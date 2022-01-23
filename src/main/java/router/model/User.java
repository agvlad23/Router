package router.model;

import java.util.List;

public class User {


        private String userId;


        private String name;

        private String group="none";
        private List<Tracking> tracking;

        public User(String userId) {
            this.userId = userId;
        }

        public User() {
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public List<Tracking> getTracking() {
            return tracking;
        }

        public void setTracking(List<Tracking> tracking) {
            this.tracking = tracking;
        }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
