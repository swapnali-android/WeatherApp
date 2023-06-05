package com.swapnali.weatherapp.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {City.class}, version = 1)
public abstract class CityDatabase extends RoomDatabase {
   public abstract CityDao cityDao();

   private static CityDatabase INSTANCE;

   public static void initialize(Context context) {
      if (INSTANCE == null) {
         INSTANCE = Room.databaseBuilder(
                 context.getApplicationContext(),
                 CityDatabase.class,
                 "my-database"
         ).build();
      }
   }

   public static CityDatabase getInstance() {
      if (INSTANCE == null) {
         throw new IllegalStateException("CityDatabase must be initialized.");
      }
      return INSTANCE;
   }
}

