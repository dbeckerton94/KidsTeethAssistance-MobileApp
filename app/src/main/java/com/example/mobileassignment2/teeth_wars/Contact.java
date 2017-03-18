package com.example.mobileassignment2.teeth_wars;


// Database to Hold Information
public class Contact {
    String pname ,psname ,puname ,ppass, pavatar;
    Integer pcoins;

    // Gets Users Name
    public void setName(String name)
    {
        this.pname = name;
    }
    public String getName()
    {
        return this.pname;
    }

    //Gets Users Second Name
    public void setSname(String sname)
    {
        this.psname = sname;
    }
    public String getSname()
    {
        return this.psname;
    }

    //Gets Username
    public void setUname(String uname)
    {
        this.puname = uname;
    }
    public String getUname()
    {
        return this.puname;
    }

    //Sets Password
    public void setPass(String pass)
    {
        this.ppass = pass;
    }
    public String getPass()
    {
        return this.ppass;
    }

    //Sets Avatar
    public void setAvatar(String avatars)
    {
        this.pavatar = avatars;
    }
    public String getAvatar()
    {
        return this.pavatar;
    }

    // Sey Coins
    public void setCoins(Integer coin)
    {
        this.pcoins = coin;
    }
    public Integer getCoins()
    {
        return this.pcoins;
    }


}
