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
package org.chtijbug.drools.logging;

/**
 * Created by alexandre on 17/07/2014.
 */
public class Page {
    private Integer currentIndex;
    private Long totalCount;
    /**
     * Setting a default value tha we can override
     */
    private Integer maxItemPerPage = 5;

    public Page() {/** nop */}

    public Page(Integer currentIndex, Long totalCount, Integer maxItemPerPage) {
        this.currentIndex = currentIndex;
        this.totalCount = totalCount;
        this.maxItemPerPage = maxItemPerPage;
    }

    public Integer getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(Integer currentIndex) {
        this.currentIndex = currentIndex;
    }

    public Long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Long totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getMaxItemPerPage() {
        return maxItemPerPage;
    }

    public void setMaxItemPerPage(Integer maxItemPerPage) {
        this.maxItemPerPage = maxItemPerPage;
    }
}
