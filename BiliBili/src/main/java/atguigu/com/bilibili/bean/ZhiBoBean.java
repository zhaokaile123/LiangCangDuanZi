package atguigu.com.bilibili.bean;

import java.util.List;

/**
 * Created by ASUS on 2017/7/25.
 */

public class ZhiBoBean {


    private String message;
    private DataBean data;


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<BannerBean> banner;
        private List<EntranceIconsBean> entranceIcons;
        private List<PartitionsBean> partitions;
        private List<NavigatorBean> navigator;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<EntranceIconsBean> getEntranceIcons() {
            return entranceIcons;
        }

        public void setEntranceIcons(List<EntranceIconsBean> entranceIcons) {
            this.entranceIcons = entranceIcons;
        }

        public List<PartitionsBean> getPartitions() {
            return partitions;
        }

        public void setPartitions(List<PartitionsBean> partitions) {
            this.partitions = partitions;
        }

        public List<NavigatorBean> getNavigator() {
            return navigator;
        }

        public void setNavigator(List<NavigatorBean> navigator) {
            this.navigator = navigator;
        }

        public static class BannerBean {
            /**
             * title : 哔哩哔哩官方音乐台
             * img : http://i2.hdslb.com/u_user/77ff62edd818248945ee734557cbc2df.jpg
             * remark : 哔哩哔哩官方音乐台
             * link : bilibili://live/23058
             */

            private String title;
            private String img;
            private String remark;
            private String link;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getRemark() {
                return remark;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }
        }

        public static class EntranceIconsBean {
            /**
             * id : 9
             * name : 绘画专区
             * entrance_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/android/big/hdpi/9_big.png?20170717153252","height":"66","width":"66"}
             */

            private int id;
            private String name;
            private EntranceIconBean entrance_icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public EntranceIconBean getEntrance_icon() {
                return entrance_icon;
            }

            public void setEntrance_icon(EntranceIconBean entrance_icon) {
                this.entrance_icon = entrance_icon;
            }

            public static class EntranceIconBean {
                /**
                 * src : http://static.hdslb.com/live-static/images/mobile/android/big/hdpi/9_big.png?20170717153252
                 * height : 66
                 * width : 66
                 */

                private String src;
                private String height;
                private String width;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }
            }
        }

        public static class PartitionsBean {
            /**
             * partition : {"id":9,"name":"绘画专区","area":"draw","sub_icon":{"src":"http://static.hdslb.com/live-static/images/mobile/android/small/hdpi/9.png?20170717153252","height":"32","width":"32"},"count":107}
             * lives : [{"owner":{"face":"http://i1.hdslb.com/bfs/face/781d9ca345ec532c621096590af52bd921922086.jpg","mid":3466280,"name":"吉戾"},"cover":{"src":"http://i0.hdslb.com/bfs/live/ef08b92f461b4ecdceeff13f40dfbf81532419fa.jpg","height":180,"width":320},"title":"×","room_id":268445,"check_version":0,"online":115,"area":"绘画专区","area_id":9,"playurl":"http://txy.live-play.acgvideo.com/live-txy/498340/live_3466280_7885818.flv?wsSecret=c61e3ebfcbe22eb760cf3465b12c3de1&wsTime=1500951842","accept_quality":"4","broadcast_type":1,"is_tv":0},{"owner":{"face":"http://i1.hdslb.com/bfs/face/c19af3d222b3ae3385013a1f342e11004b40abc6.jpg","mid":2468660,"name":"七口山雨"},"cover":{"src":"http://i0.hdslb.com/bfs/live/e49e1f8be9a05e77efa1ac22d69af9e9274e4d06.jpg","height":180,"width":320},"title":"透透又画不进画了","room_id":156417,"check_version":0,"online":2436,"area":"绘画专区","area_id":9,"playurl":"http://live-play.acgvideo.com/live/835/live_2468660_2309513.flv?wsSecret=963f38eda9fee3dadbcfafbd58161d58&wsTime=594f2f2a","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i2.hdslb.com/bfs/face/4b6eb872d4c29352bcea4b110bd04aef022ddf58.jpg","mid":97249841,"name":"欢乐颂娜娜"},"cover":{"src":"http://i0.hdslb.com/bfs/live/b0dbe9d79ef661dffee9cc79385f466f501e97cc.jpg","height":180,"width":320},"title":"临摹一个少女","room_id":4095423,"check_version":0,"online":175,"area":"绘画专区","area_id":9,"playurl":"http://live-play.acgvideo.com/live/866/live_97249841_9571498.flv?wsSecret=9a8d878484d2507d922cedd72c7abdbf&wsTime=594f2f2a","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i0.hdslb.com/bfs/face/b2bcea45afafbddfac1f052a7966c17804d04f13.jpg","mid":571362,"name":"木偶不想画画"},"cover":{"src":"http://i0.hdslb.com/bfs/live/526466f26f1a86c75924ca2b57b0d1369b5fac81.jpg","height":180,"width":320},"title":"b站活动求投票","room_id":30191,"check_version":0,"online":3364,"area":"绘画专区","area_id":9,"playurl":"http://txy.live-play.acgvideo.com/live-txy/180724/live_571362_332_c521e483.flv?wsSecret=4c5a610dcc695e498df86762d7a6c4a4&wsTime=1500951842","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i0.hdslb.com/bfs/face/677ac77657535537ecd76f1051a11951e2be5164.jpg","mid":9871569,"name":"魔法少女小逝"},"cover":{"src":"http://i0.hdslb.com/bfs/live/e02e3b4f71fa8c57793d70fbccb6edbd5883d14a.jpg","height":180,"width":320},"title":"【声控福利】今天不画本子","room_id":73088,"check_version":0,"online":237,"area":"绘画专区","area_id":9,"playurl":"http://live-play.acgvideo.com/live/441/live_9871569_3091457.flv?wsSecret=36ceaa5354ecb6da0f66cf3445a72780&wsTime=594f2f2a","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i0.hdslb.com/bfs/face/66d0bbecbb979c6b8c4cbaf2587163f89b8fdfb9.jpg","mid":2456830,"name":"飛鸾"},"cover":{"src":"http://i0.hdslb.com/bfs/live/40cd190e7ceb2f90412b52de03354719c263cda9.jpg","height":180,"width":320},"title":"业余选手的漫漫自学之路(Day 2)","room_id":109362,"check_version":0,"online":221,"area":"绘画专区","area_id":9,"playurl":"http://dl.live-play.acgvideo.com/live-dl/192028/live_2456830_5759655.flv?wsSecret=7d1908a3b797bd95434445c513bea441&wsTime=1500951842","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i2.hdslb.com/bfs/face/436691bfa02a0bf22362aa835b7bdf6b3e42790b.jpg","mid":503712,"name":"小小作文"},"cover":{"src":"http://i0.hdslb.com/bfs/live/9092c2d746fb556dfe2b7563ba22f00204a2bb96.jpg","height":180,"width":320},"title":"穿越西元3000后","room_id":209462,"check_version":0,"online":499,"area":"绘画专区","area_id":9,"playurl":"http://txy.live-play.acgvideo.com/live-txy/207579/live_503712_2556310.flv?wsSecret=19014bc535e075d973d89a11cceb07a7&wsTime=1500951842","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i0.hdslb.com/bfs/face/15251f69a1174a3f33cb24fb9476f585c84d3aea.jpg","mid":31845587,"name":"次世代玄明"},"cover":{"src":"http://i0.hdslb.com/bfs/live/7db7009a1bc9871f59cc7d0f33c246bfba47973e.jpg","height":180,"width":320},"title":"画画  进来可以点歌听","room_id":276949,"check_version":0,"online":47,"area":"绘画专区","area_id":9,"playurl":"http://live-play.acgvideo.com/live/145/live_31845587_1200271.flv?wsSecret=7452d7c6d19de331cda7c815dc094ae9&wsTime=594f2f2a","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i1.hdslb.com/bfs/face/4230324dea7cc5beebbb6803ed6db8b0c5d50790.jpg","mid":23754054,"name":"泽君夔"},"cover":{"src":"http://i0.hdslb.com/bfs/live/41fda8ec9d9b3357525bc5b373e4d8af8f1057c8.jpg","height":180,"width":320},"title":"ASMR向自然声，海潮","room_id":1810777,"check_version":0,"online":68,"area":"绘画专区","area_id":9,"playurl":"http://txy.live-play.acgvideo.com/live-txy/882037/live_23754054_3530252.flv?wsSecret=37a112ae1e2c431654c8a28d2761399b&wsTime=1500951842","accept_quality":"4","broadcast_type":0,"is_tv":0},{"owner":{"face":"http://i2.hdslb.com/bfs/face/56157d60c8c2c0b8f7e137262bbb2e577c95c7a0.png","mid":5050136,"name":"Kirito丶桐人君"},"cover":{"src":"http://i0.hdslb.com/bfs/live/375e1b19a907b9cfa85dbd41f6ab98f6ac5a602a.jpg","height":180,"width":320},"title":"【日常小清新】超级小清新主播在此（雾）。","room_id":73945,"check_version":0,"online":1249,"area":"绘画专区","area_id":9,"playurl":"http://txy.live-play.acgvideo.com/live-txy/911352/live_5050136_7329209.flv?wsSecret=36466ed8e75809857134c3780756ef68&wsTime=1500951842","accept_quality":"4","broadcast_type":0,"is_tv":0}]
             */

            private PartitionBean partition;
            private List<LivesBean> lives;

            public PartitionBean getPartition() {
                return partition;
            }

            public void setPartition(PartitionBean partition) {
                this.partition = partition;
            }

            public List<LivesBean> getLives() {
                return lives;
            }

            public void setLives(List<LivesBean> lives) {
                this.lives = lives;
            }

            public static class PartitionBean {
                /**
                 * id : 9
                 * name : 绘画专区
                 * area : draw
                 * sub_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/android/small/hdpi/9.png?20170717153252","height":"32","width":"32"}
                 * count : 107
                 */

                private int id;
                private String name;
                private String area;
                private SubIconBean sub_icon;
                private int count;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public SubIconBean getSub_icon() {
                    return sub_icon;
                }

                public void setSub_icon(SubIconBean sub_icon) {
                    this.sub_icon = sub_icon;
                }

                public int getCount() {
                    return count;
                }

                public void setCount(int count) {
                    this.count = count;
                }

                public static class SubIconBean {
                    /**
                     * src : http://static.hdslb.com/live-static/images/mobile/android/small/hdpi/9.png?20170717153252
                     * height : 32
                     * width : 32
                     */

                    private String src;
                    private String height;
                    private String width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public String getHeight() {
                        return height;
                    }

                    public void setHeight(String height) {
                        this.height = height;
                    }

                    public String getWidth() {
                        return width;
                    }

                    public void setWidth(String width) {
                        this.width = width;
                    }
                }
            }

            public static class LivesBean {
                /**
                 * owner : {"face":"http://i1.hdslb.com/bfs/face/781d9ca345ec532c621096590af52bd921922086.jpg","mid":3466280,"name":"吉戾"}
                 * cover : {"src":"http://i0.hdslb.com/bfs/live/ef08b92f461b4ecdceeff13f40dfbf81532419fa.jpg","height":180,"width":320}
                 * title : ×
                 * room_id : 268445
                 * check_version : 0
                 * online : 115
                 * area : 绘画专区
                 * area_id : 9
                 * playurl : http://txy.live-play.acgvideo.com/live-txy/498340/live_3466280_7885818.flv?wsSecret=c61e3ebfcbe22eb760cf3465b12c3de1&wsTime=1500951842
                 * accept_quality : 4
                 * broadcast_type : 1
                 * is_tv : 0
                 */

                private OwnerBean owner;
                private CoverBean cover;
                private String title;
                private int room_id;
                private int check_version;
                private int online;
                private String area;
                private int area_id;
                private String playurl;
                private String accept_quality;
                private int broadcast_type;
                private int is_tv;

                public OwnerBean getOwner() {
                    return owner;
                }

                public void setOwner(OwnerBean owner) {
                    this.owner = owner;
                }

                public CoverBean getCover() {
                    return cover;
                }

                public void setCover(CoverBean cover) {
                    this.cover = cover;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public int getRoom_id() {
                    return room_id;
                }

                public void setRoom_id(int room_id) {
                    this.room_id = room_id;
                }

                public int getCheck_version() {
                    return check_version;
                }

                public void setCheck_version(int check_version) {
                    this.check_version = check_version;
                }

                public int getOnline() {
                    return online;
                }

                public void setOnline(int online) {
                    this.online = online;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public int getArea_id() {
                    return area_id;
                }

                public void setArea_id(int area_id) {
                    this.area_id = area_id;
                }

                public String getPlayurl() {
                    return playurl;
                }

                public void setPlayurl(String playurl) {
                    this.playurl = playurl;
                }

                public String getAccept_quality() {
                    return accept_quality;
                }

                public void setAccept_quality(String accept_quality) {
                    this.accept_quality = accept_quality;
                }

                public int getBroadcast_type() {
                    return broadcast_type;
                }

                public void setBroadcast_type(int broadcast_type) {
                    this.broadcast_type = broadcast_type;
                }

                public int getIs_tv() {
                    return is_tv;
                }

                public void setIs_tv(int is_tv) {
                    this.is_tv = is_tv;
                }

                public static class OwnerBean {
                    /**
                     * face : http://i1.hdslb.com/bfs/face/781d9ca345ec532c621096590af52bd921922086.jpg
                     * mid : 3466280
                     * name : 吉戾
                     */

                    private String face;
                    private int mid;
                    private String name;

                    public String getFace() {
                        return face;
                    }

                    public void setFace(String face) {
                        this.face = face;
                    }

                    public int getMid() {
                        return mid;
                    }

                    public void setMid(int mid) {
                        this.mid = mid;
                    }

                    public String getName() {
                        return name;
                    }

                    public void setName(String name) {
                        this.name = name;
                    }
                }

                public static class CoverBean {
                    /**
                     * src : http://i0.hdslb.com/bfs/live/ef08b92f461b4ecdceeff13f40dfbf81532419fa.jpg
                     * height : 180
                     * width : 320
                     */

                    private String src;
                    private int height;
                    private int width;

                    public String getSrc() {
                        return src;
                    }

                    public void setSrc(String src) {
                        this.src = src;
                    }

                    public int getHeight() {
                        return height;
                    }

                    public void setHeight(int height) {
                        this.height = height;
                    }

                    public int getWidth() {
                        return width;
                    }

                    public void setWidth(int width) {
                        this.width = width;
                    }
                }
            }
        }

        public static class NavigatorBean {
            /**
             * id : 9
             * name : 绘画
             * entrance_icon : {"src":"http://static.hdslb.com/live-static/images/mobile/blink/9_2x.png?20170717153252","height":"66","width":"66"}
             */

            private int id;
            private String name;
            private EntranceIconBeanX entrance_icon;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public EntranceIconBeanX getEntrance_icon() {
                return entrance_icon;
            }

            public void setEntrance_icon(EntranceIconBeanX entrance_icon) {
                this.entrance_icon = entrance_icon;
            }

            public static class EntranceIconBeanX {
                /**
                 * src : http://static.hdslb.com/live-static/images/mobile/blink/9_2x.png?20170717153252
                 * height : 66
                 * width : 66
                 */

                private String src;
                private String height;
                private String width;

                public String getSrc() {
                    return src;
                }

                public void setSrc(String src) {
                    this.src = src;
                }

                public String getHeight() {
                    return height;
                }

                public void setHeight(String height) {
                    this.height = height;
                }

                public String getWidth() {
                    return width;
                }

                public void setWidth(String width) {
                    this.width = width;
                }
            }
        }
    }
}
