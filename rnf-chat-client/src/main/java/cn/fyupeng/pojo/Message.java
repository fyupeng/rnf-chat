package cn.fyupeng.pojo;

import java.io.Serializable;
import java.util.Date;

/**
 * @Auther: fyp
 * @Date: 2023/5/26
 * @Description: 信息载体
 * @Package: cn.fyupeng.pojo
 * @Version: 1.0
 */
public class Message implements Serializable {
   private String message;
   private String userName;
   private Date createTime;

   public String getMessage() {
      return message;
   }

   public void setMessage(String message) {
      this.message = message;
   }

   public String getUserName() {
      return userName;
   }

   public void setUserName(String userName) {
      this.userName = userName;
   }

   public Date getCreateTime() {
      return createTime;
   }

   public void setCreateTime(Date createTime) {
      this.createTime = createTime;
   }

   @Override
   public String toString() {
      return "Message{" +
              "message='" + message + '\'' +
              ", userName='" + userName + '\'' +
              ", createTime=" + createTime +
              '}';
   }
}
