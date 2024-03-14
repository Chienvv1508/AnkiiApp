package com.example.theghinho.Model;

public class User {

   private String Lname;
   private String FName;
   private String Email;
   private String UserName;



   public String getLname() {
      return Lname;
   }

   public void setLname(String lname) {
      Lname = lname;
   }

   public String getFName() {
      return FName;
   }

   public void setFName(String FName) {
      this.FName = FName;
   }

   public String getEmail() {
      return Email;
   }

   public void setEmail(String email) {
      Email = email;
   }

   public String getUserName() {
      return UserName;
   }

   public void setUserName(String userName) {
      UserName = userName;
   }

   public String getPassword() {
      return Password;
   }

   public void setPassword(String password) {
      Password = password;
   }

   public int getRoleId() {
      return RoleId;
   }

   public void setRoleId(int roleId) {
      RoleId = roleId;
   }

   private String Password;

   private  int RoleId;

   public User (){

   }


}
