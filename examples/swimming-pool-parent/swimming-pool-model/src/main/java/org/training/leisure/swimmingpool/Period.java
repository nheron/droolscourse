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

import java.util.Date;


public class Period {
    private Date desidedStartDate;
    private String seasonType;

    public Period() {
    }

    public Date getDesidedStartDate() {
        return desidedStartDate;
    }

    public void setDesidedStartDate(Date desidedStartDate) {
        this.desidedStartDate = desidedStartDate;
    }

    public String getSeasonType() {
        return seasonType;
    }

    public void setSeasonType(String seasonType) {
        this.seasonType = seasonType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Period{");
        sb.append("desidedStartDate=").append(desidedStartDate);
        sb.append(", seasonType=").append(seasonType);
        sb.append('}');
        return sb.toString();
    }
}
