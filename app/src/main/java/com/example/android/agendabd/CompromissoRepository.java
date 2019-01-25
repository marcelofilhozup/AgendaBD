package com.example.android.agendabd;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class CompromissoRepository {

    private CompromissoDao mCompromissoDao;
    private LiveData<List<Compromisso>> mAllCompromissos;

    CompromissoRepository(Application application){
        CompromissoRoomDatabase db = CompromissoRoomDatabase.getDatabase(application);

        mCompromissoDao = db.compromissoDao();
        mAllCompromissos = mCompromissoDao.getAllCompromissos();
    }

    LiveData<List<Compromisso>> getAllCompromissos() {
        return mAllCompromissos;
    }

    public void insert (Compromisso compromisso) {
        new insertAsyncTask(mCompromissoDao).execute(compromisso);
    }

    public void deleteCompromisso(String comp)  {
        new deleteWordAsyncTask(mCompromissoDao).execute(comp);
    }

    public void updateCompromisso(Compromisso comp)  {
        new updateWordAsyncTask(mCompromissoDao).execute(comp);
    }

    private static class insertAsyncTask extends AsyncTask<Compromisso, Void, Void> {

        private CompromissoDao mAsyncTaskDao;

        insertAsyncTask(CompromissoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Compromisso... params) {
            mAsyncTaskDao.insert(params[0]);


            return null;
        }
    }

    private static class deleteWordAsyncTask extends AsyncTask<String, Void, Void> {
        private CompromissoDao mAsyncTaskDao;

        deleteWordAsyncTask(CompromissoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final String... params) {
            mAsyncTaskDao.deleteCompromisso(params[0]);
            return null;
        }
    }

    private static class updateWordAsyncTask extends AsyncTask<Compromisso, Void, Void> {
        private CompromissoDao mAsyncTaskDao;

        updateWordAsyncTask(CompromissoDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Compromisso... params) {



            mAsyncTaskDao.updateCompromisso(params[0]);



            return null;
        }
    }
}
