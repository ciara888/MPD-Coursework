package com.example.equakefinal;
import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Item implements Parcelable {
    // Ciara Scott S1709819
        private String title;
        private String description;
        private String link;
           private int depth;
        private String pubDate;
        private String category;
        private double geoLat;
        private double geoLong;
        private double strength;
        private String location;


    public Item(){
            title = "";
            description ="";
            link = "";
            pubDate = "";
            category = "";
            location = "";
            geoLat = 0;
            geoLong = 0;
            strength = 0;
        }



    protected Item(Parcel in) {
            title = in.readString();
              link = in.readString();
            description = in.readString();
            pubDate = in.readString();
            category = in.readString();
            geoLat = in.readDouble();
            geoLong = in.readDouble();
            strength = in.readDouble();
            location = in.readString();
            depth = in.readInt();
        }
    public Item(String aTitle, String aDescription, String aLink, String aPubDate, String aCategory, double aGeoLat, double aGeoLong, double aStrength, String aLocation){
        title = aTitle;
        description = aDescription;
        link = aLink;
        pubDate = aPubDate;
        category = aCategory;
        geoLat = aGeoLat;
        geoLong = aGeoLong;
        strength = aStrength;
        location = aLocation;
    }

        public int getDepth() {
            return depth;
        }

        public void setDepth(int depth) {
            this.depth = depth;
        }

        public double getStrength() {
            return strength;
        }

        public void setStrength(double strength) {
            this.strength = strength;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
            String[] descriptionArray = this.description.split("\\s*;\\s*");


            String longlat = descriptionArray[2];
            String [] longlatArray = longlat.split("\\s*:\\s*");
            String longlatNum = longlatArray[1];
            String [] longlatNumArray = longlatNum.split("\\s*,\\s*");
            setGeoLat(Double.parseDouble(longlatNumArray[0]));
            setGeoLong(Double.parseDouble(longlatNumArray[1]));


            String mag = descriptionArray[4];
            String [] magArray = mag.split("\\s*:\\s*");
            String strength = magArray[1];
            setStrength(Double.parseDouble(strength));

            String loc = descriptionArray[1];
            String[] locArray = loc.split("\\s*:\\s*");
            String locName = locArray[1];
            setLocation(locName);

            String depth = descriptionArray[3];
            String[] depthArray = depth.split("\\s*:\\s*");
            String depthName = depthArray[1];
            String[] depthNumArray = depthName.split("\\s* \\s*");
            String depthNum = depthNumArray[0];
            setDepth(Integer.parseInt(depthNum));
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public Date getPubDate() throws ParseException {
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter=new SimpleDateFormat("E, dd MMM yyyy HH:mm:ss");
            return formatter.parse(pubDate);
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public double getGeoLat() {
            return geoLat;
        }

        public void setGeoLat(double geoLat) {
            this.geoLat = geoLat;
        }

        public double getGeoLong() {
            return geoLong;
        }

        public void setGeoLong(double geoLong) {
            this.geoLong = geoLong;
        }


    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public com.example.equakefinal.Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public com.example.equakefinal.Item[] newArray(int size) {
            return new Item[size];
        }
    };





        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(title);
            dest.writeString(description);
            dest.writeString(link);
            dest.writeString(pubDate);
            dest.writeString(category);
            dest.writeDouble(geoLat);
            dest.writeDouble(geoLong);
            dest.writeDouble(strength);
            dest.writeString(location);
            dest.writeInt(depth);
        }
}
