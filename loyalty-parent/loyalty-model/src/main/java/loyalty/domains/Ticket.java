/*
 * Copyright 2014 Pymma Software
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package loyalty.domains;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Ticket {

    private String Id;
    private Date dateTicket;
    private Float amount = 0F;
    private Card loyaltyCard;
    private Customer customer;
    private List<TicketLine> ticketLines = new ArrayList<TicketLine>();

    public Ticket() {
        super();
        // TODO Auto-generated constructor stub
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Card getLoyaltyCard() {
        return loyaltyCard;
    }

    public void setLoyaltyCard(Card loyaltyCard) {
        this.loyaltyCard = loyaltyCard;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Date getDateTicket() {
        return dateTicket;
    }

    public void setDateTicket(Date date) {
        this.dateTicket = date;
    }

    public long addLine(Product product, Float price, long quantity) {
        TicketLine newLine = new TicketLine(null, product, quantity, price);
        ticketLines.add(newLine);
        this.amount = this.amount + newLine.getLineTotal();
        return newLine.getLineNumber();
    }

    public void delLine(int lineNumber) {
        if (lineNumber <= this.ticketLines.size()) {
            TicketLine lineToDel = this.ticketLines.get(lineNumber);
            lineToDel.setValid(false);
            this.amount = this.amount - lineToDel.getLineTotal();
        }
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public List<TicketLine> getTicketLines() {
        return ticketLines;
    }

    public void setTicketLines(List<TicketLine> ticketLines) {
        this.ticketLines = ticketLines;
    }

}
