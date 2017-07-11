package atguigu.com.liangcangduanzi.bean;

/**
 * Created by ASUS on 2017/7/8.
 */

public class Daren_ItmsBean {

    /**
     * meta : {"status":0,"server_time":"2017-07-08 17:06:31","account_id":0,"cost":0.012424945831299,"errdata":null,"errmsg":""}
     * version : 1
     * data : {"has_more":false,"num_items":"0","items":{"user_id":"12596","user_name":"山本耀司","is_daren":"1","email":"yini.ma@yohjiyamamoto.co.jp","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/12/12596.jpg?t=1499504791","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/12/12596.jpg?t=1499504791","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/12/12596.jpg?t=1499504791"},"user_desc":"日本时装设计师","friend":"0","like_count":"0","recommendation_count":"3","following_count":"2","followed_count":"20728","template_id":"0"}}
     */

    private MetaBean meta;
    private int version;
    private DataBean data;

    public MetaBean getMeta() {
        return meta;
    }

    public void setMeta(MetaBean meta) {
        this.meta = meta;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class MetaBean {
        /**
         * status : 0
         * server_time : 2017-07-08 17:06:31
         * account_id : 0
         * cost : 0.012424945831299
         * errdata : null
         * errmsg :
         */

        private int status;
        private String server_time;
        private int account_id;
        private double cost;
        private Object errdata;
        private String errmsg;

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getServer_time() {
            return server_time;
        }

        public void setServer_time(String server_time) {
            this.server_time = server_time;
        }

        public int getAccount_id() {
            return account_id;
        }

        public void setAccount_id(int account_id) {
            this.account_id = account_id;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public Object getErrdata() {
            return errdata;
        }

        public void setErrdata(Object errdata) {
            this.errdata = errdata;
        }

        public String getErrmsg() {
            return errmsg;
        }

        public void setErrmsg(String errmsg) {
            this.errmsg = errmsg;
        }
    }

    public static class DataBean {
        /**
         * has_more : false
         * num_items : 0
         * items : {"user_id":"12596","user_name":"山本耀司","is_daren":"1","email":"yini.ma@yohjiyamamoto.co.jp","user_image":{"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/12/12596.jpg?t=1499504791","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/12/12596.jpg?t=1499504791","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/12/12596.jpg?t=1499504791"},"user_desc":"日本时装设计师","friend":"0","like_count":"0","recommendation_count":"3","following_count":"2","followed_count":"20728","template_id":"0"}
         */

        private boolean has_more;
        private String num_items;
        private ItemsBean items;

        public boolean isHas_more() {
            return has_more;
        }

        public void setHas_more(boolean has_more) {
            this.has_more = has_more;
        }

        public String getNum_items() {
            return num_items;
        }

        public void setNum_items(String num_items) {
            this.num_items = num_items;
        }

        public ItemsBean getItems() {
            return items;
        }

        public void setItems(ItemsBean items) {
            this.items = items;
        }

        public static class ItemsBean {
            /**
             * user_id : 12596
             * user_name : 山本耀司
             * is_daren : 1
             * email : yini.ma@yohjiyamamoto.co.jp
             * user_image : {"self_img":"1","orig":"http://imgs-qn.iliangcang.com/ware/userhead/orig/2/12/12596.jpg?t=1499504791","mid":"http://imgs-qn.iliangcang.com/ware/userhead/mid/2/12/12596.jpg?t=1499504791","tmb":"http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/12/12596.jpg?t=1499504791"}
             * user_desc : 日本时装设计师
             * friend : 0
             * like_count : 0
             * recommendation_count : 3
             * following_count : 2
             * followed_count : 20728
             * template_id : 0
             */

            private String user_id;
            private String user_name;
            private String is_daren;
            private String email;
            private UserImageBean user_image;
            private String user_desc;
            private String friend;
            private String like_count;
            private String recommendation_count;
            private String following_count;
            private String followed_count;
            private String template_id;

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getIs_daren() {
                return is_daren;
            }

            public void setIs_daren(String is_daren) {
                this.is_daren = is_daren;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public UserImageBean getUser_image() {
                return user_image;
            }

            public void setUser_image(UserImageBean user_image) {
                this.user_image = user_image;
            }

            public String getUser_desc() {
                return user_desc;
            }

            public void setUser_desc(String user_desc) {
                this.user_desc = user_desc;
            }

            public String getFriend() {
                return friend;
            }

            public void setFriend(String friend) {
                this.friend = friend;
            }

            public String getLike_count() {
                return like_count;
            }

            public void setLike_count(String like_count) {
                this.like_count = like_count;
            }

            public String getRecommendation_count() {
                return recommendation_count;
            }

            public void setRecommendation_count(String recommendation_count) {
                this.recommendation_count = recommendation_count;
            }

            public String getFollowing_count() {
                return following_count;
            }

            public void setFollowing_count(String following_count) {
                this.following_count = following_count;
            }

            public String getFollowed_count() {
                return followed_count;
            }

            public void setFollowed_count(String followed_count) {
                this.followed_count = followed_count;
            }

            public String getTemplate_id() {
                return template_id;
            }

            public void setTemplate_id(String template_id) {
                this.template_id = template_id;
            }

            public static class UserImageBean {
                /**
                 * self_img : 1
                 * orig : http://imgs-qn.iliangcang.com/ware/userhead/orig/2/12/12596.jpg?t=1499504791
                 * mid : http://imgs-qn.iliangcang.com/ware/userhead/mid/2/12/12596.jpg?t=1499504791
                 * tmb : http://imgs-qn.iliangcang.com/ware/userhead/tmb/2/12/12596.jpg?t=1499504791
                 */

                private String self_img;
                private String orig;
                private String mid;
                private String tmb;

                public String getSelf_img() {
                    return self_img;
                }

                public void setSelf_img(String self_img) {
                    this.self_img = self_img;
                }

                public String getOrig() {
                    return orig;
                }

                public void setOrig(String orig) {
                    this.orig = orig;
                }

                public String getMid() {
                    return mid;
                }

                public void setMid(String mid) {
                    this.mid = mid;
                }

                public String getTmb() {
                    return tmb;
                }

                public void setTmb(String tmb) {
                    this.tmb = tmb;
                }
            }
        }
    }
}
