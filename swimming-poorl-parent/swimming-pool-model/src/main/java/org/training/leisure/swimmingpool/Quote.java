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
package org.training.leisure.swimmingpool;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Quote {
    private Date quoteDate;
    private Date validUntil;
    private Period period;
    private Subscription subscription;
    private List<Person> personList = new ArrayList<>();
    private Address address;
    private List<Price> priceList = new ArrayList<>();
    private String sessionLogging;

    public Quote() {
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }

    public Date getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(Date validUntil) {
        this.validUntil = validUntil;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Price> getPriceList() {
        return priceList;
    }

    public void setPriceList(List<Price> priceList) {
        this.priceList = priceList;
    }

    public String getSessionLogging() {
        return sessionLogging;
    }

    public void setSessionLogging(String sessionLogging) {
        this.sessionLogging = sessionLogging;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Quote{");
        sb.append("quoteDate=").append(quoteDate);
        sb.append(", validUntil=").append(validUntil);
        sb.append(", period=").append(period);
        sb.append(", subscription=").append(subscription);
        sb.append(", personList=").append(personList);
        sb.append(", address=").append(address);
        sb.append(", priceList=").append(priceList);
        sb.append('}');
        return sb.toString();
    }
}
