/*
 Copyright [2016] [Taqdir Ali]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

 */
package org.uclab.mm.datamodel.sc;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Taqdir
 */
@Entity
public class UserPreferredActivities implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Long userPreferredActivityId;
    private Long userId;
    private int activityId;
    private int preferenceLevelId;
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UserPreferredActivities)) {
            return false;
        }
        UserPreferredActivities other = (UserPreferredActivities) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.uclab.mm.datamodel.sc.UserPreferredActivities[ id=" + id + " ]";
    }

    /**
     * @return the userPreferredActivityId
     */
    public Long getUserPreferredActivityId() {
        return userPreferredActivityId;
    }

    /**
     * @param userPreferredActivityId the userPreferredActivityId to set
     */
    public void setUserPreferredActivityId(Long userPreferredActivityId) {
        this.userPreferredActivityId = userPreferredActivityId;
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the activityId
     */
    public int getActivityId() {
        return activityId;
    }

    /**
     * @param activityId the activityId to set
     */
    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    /**
     * @return the preferenceLevelId
     */
    public int getPreferenceLevelId() {
        return preferenceLevelId;
    }

    /**
     * @param preferenceLevelId the preferenceLevelId to set
     */
    public void setPreferenceLevelId(int preferenceLevelId) {
        this.preferenceLevelId = preferenceLevelId;
    }
    
}
