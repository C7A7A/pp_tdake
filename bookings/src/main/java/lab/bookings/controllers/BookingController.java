package lab.bookings.controllers;

import jakarta.validation.Valid;
import lab.bookings.models.Apartment;
import lab.bookings.models.Booking;
import lab.bookings.repositories.ApartmentRepository;
import lab.bookings.repositories.BookingRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/bookings")
public class BookingController {
    private BookingRepository bookingRepository;
    private ApartmentRepository apartmentRepository;
    public BookingController(BookingRepository bookingRepository,
                             ApartmentRepository apartmentRepository) {
        this.bookingRepository = bookingRepository;
        this.apartmentRepository = apartmentRepository;
    }
    @GetMapping
    public String getAll(Model model) {
        model.addAttribute(new Booking());
        model.addAttribute("bookings", bookingRepository.findAll());
        return "bookings";
    }
    @PostMapping
    public String create(@Valid Booking booking, Errors errors,
                         Model model) {
        String view;
        if (errors.hasErrors()) {
            model.addAttribute("bookings",
                    bookingRepository.findAll());
            view = "bookings";
        } else {
            int numGuests = booking.getNumGuests();
            LocalDate startDay = booking.getFromDate();
            LocalDate endDay = booking.getToDate();
            List<Apartment> availableApartments = apartmentRepository.getFreeApartments(numGuests, startDay, endDay);
            if(availableApartments.size() > 0) {
                booking.setApartment(availableApartments.get(0));
                bookingRepository.save(booking);
                view = "redirect:/bookings";
            } else {
                model.addAttribute("noApartmentAvailable", true);
                model.addAttribute("bookings",
                        bookingRepository.findAll());
                view = "bookings";
            }
        }
        return view;
    }

    @PostMapping("delete")
    public String delete(@RequestParam("id") Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);

        if (booking.isEmpty()) {
            return "bookings";
        }

        bookingRepository.delete(booking.get());
        return "redirect:/bookings";
    }
}