package com.example.android.agendabd;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {Compromisso.class}, version = 2)

public abstract class CompromissoRoomDatabase extends RoomDatabase {

    public abstract CompromissoDao compromissoDao();

    private static volatile CompromissoRoomDatabase INSTANCE;

    static CompromissoRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (CompromissoRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CompromissoRoomDatabase.class, "Compromisso_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final CompromissoDao mDao;

        PopulateDbAsync(CompromissoRoomDatabase db) {
            mDao = db.compromissoDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {

            return null;
        }
    }
}
