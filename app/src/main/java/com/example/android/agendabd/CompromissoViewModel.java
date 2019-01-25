package com.example.android.agendabd;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class CompromissoViewModel extends AndroidViewModel {

    private CompromissoRepository mRepository;

    private LiveData<List<Compromisso>> mAllCompromissos;

    public CompromissoViewModel(Application application){
        super(application);
        mRepository = new CompromissoRepository (application);
        mAllCompromissos = mRepository.getAllCompromissos();
    }

    LiveData<List<Compromisso>> getAllWords() { return mAllCompromissos; }

    public void insert(Compromisso compromisso) { mRepository.insert(compromisso); }
    public void deleteCompromisso(String comp) {mRepository.deleteCompromisso(comp);}
    public void updateCompromisso(Compromisso comp) {mRepository.updateCompromisso(comp);}

}
