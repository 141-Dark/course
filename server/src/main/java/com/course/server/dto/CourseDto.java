package com.course.server.dto;
        import java.math.BigDecimal;
        import java.util.Date;
        import java.util.List;

        import com.fasterxml.jackson.annotation.JsonFormat;
public class CourseDto {
        /**
        * 课程id
        */
        private String id;
        /**
        * 课程名
        */
        private String name;
        /**
        * 简介
        */
        private String summary;
        /**
        * 时长（秒）
        */
        private Integer time;
        /**
        * 价格
        */
        private BigDecimal price;
        /**
        * 封面
        */
        private String image;
        /**
        * 级别
        */
        private String level;
        /**
        * 是否收费
        */
        private String charge;
        /**
        * 课程状态
        */
        private String status;
        /**
        * 报名人数
        */
        private Integer enroll;
        /**
        * 顺序
        */
        private Integer sort;
        /**
        * 创建时间,需要统一时间格式，与数据库中的datetime保持一致
        */
        @JsonFormat(pattern = "yyyy-MM-dd:mm:ss",timezone="GMT+8")
        private Date createdAt;
        /**
        * 修改时间，数据格式保持一致
        */

        /**
         * 增加一条分类的数据，添加时间2020年6月14日22:19:48，注意要与course.vue中额的参数保持一致
         * */
        private List<CategoryDto> tree;

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("CourseDto{");
        sb.append("id='").append(id).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", summary='").append(summary).append('\'');
        sb.append(", time=").append(time);
        sb.append(", price=").append(price);
        sb.append(", image='").append(image).append('\'');
        sb.append(", level='").append(level).append('\'');
        sb.append(", charge='").append(charge).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", enroll=").append(enroll);
        sb.append(", sort=").append(sort);
        sb.append(", createdAt=").append(createdAt);
        sb.append(", tree=").append(tree);
        sb.append(", updatedAt=").append(updatedAt);
        sb.append('}');
        return sb.toString();
    }

    public List<CategoryDto> getTree() {
        return tree;
    }

    public void setTree(List<CategoryDto> tree) {
        this.tree = tree;
    }

    @JsonFormat(pattern = "yyyy-MM-dd:mm:ss",timezone="GMT+8")
        private Date updatedAt;
        public String getId(){
              return id;
        }

        public void setId(String id){
              this.id = id;
        }
        public String getName(){
              return name;
        }

        public void setName(String name){
              this.name = name;
        }
        public String getSummary(){
              return summary;
        }

        public void setSummary(String summary){
              this.summary = summary;
        }
        public Integer getTime(){
              return time;
        }

        public void setTime(Integer time){
              this.time = time;
        }
        public BigDecimal getPrice(){
              return price;
        }

        public void setPrice(BigDecimal price){
              this.price = price;
        }
        public String getImage(){
              return image;
        }

        public void setImage(String image){
              this.image = image;
        }
        public String getLevel(){
              return level;
        }

        public void setLevel(String level){
              this.level = level;
        }
        public String getCharge(){
              return charge;
        }

        public void setCharge(String charge){
              this.charge = charge;
        }
        public String getStatus(){
              return status;
        }

        public void setStatus(String status){
              this.status = status;
        }
        public Integer getEnroll(){
              return enroll;
        }

        public void setEnroll(Integer enroll){
              this.enroll = enroll;
        }
        public Integer getSort(){
              return sort;
        }

        public void setSort(Integer sort){
              this.sort = sort;
        }
        public Date getCreatedAt(){
              return createdAt;
        }

        public void setCreatedAt(Date createdAt){
              this.createdAt = createdAt;
        }
        public Date getUpdatedAt(){
              return updatedAt;
        }

        public void setUpdatedAt(Date updatedAt){
              this.updatedAt = updatedAt;
        }


}