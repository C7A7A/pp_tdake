/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package web;

import ejb.NewsItemFacadeLocal;
import ejb.NewsItem;
import jakarta.annotation.Resource;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSException;
import jakarta.jms.ObjectMessage;
import jakarta.jms.TextMessage;
import java.util.List;

/**
 *
 * @author Mateusz
 */
@Named(value = "newsBean")
@RequestScoped
public class NewsBean {

    @Inject
    private NewsItemFacadeLocal facade;
    @Inject
    private JMSContext context;
    @Resource(lookup="jms/NewsQueue")
    private jakarta.jms.Queue queue;
    
    private String headingText;
    private String bodyText;
    private String text;

     
    /**
     * Creates a new instance of NewsBean
     */
    public NewsBean() {
    }
    
    void sendNewsItem(String heading, String body) {
        try {
            ObjectMessage message = context.createObjectMessage();
            NewsItem e = new NewsItem();
            e.setHeading(heading);
            e.setBody(body);
            message.setObject(e);
            context.createProducer().send(queue, message);
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    void sendNestItemText(String text) {
        try {
            TextMessage textMessage = context.createTextMessage();
            textMessage.setText(text);
            context.createProducer().send(queue, textMessage);
            
        } catch (JMSException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<NewsItem> getNewsItems()
    {
        return facade.getAllNewsItems();
    }

    public String submitNews()
    {
        this.sendNewsItem(headingText, bodyText);
        return null;
    }
    
    public String submitNewsText()
    {
        this.sendNestItemText(text);
        return null;
    }
    
    /**
     * @return the headingText
     */
    public String getHeadingText() {
        return headingText;
    }

    /**
     * @param headingText the headingText to set
     */
    public void setHeadingText(String headingText) {
        this.headingText = headingText;
    }

    /**
     * @return the bodyText
     */
    public String getBodyText() {
        return bodyText;
    }

    /**
     * @param bodyText the bodyText to set
     */
    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }
}
