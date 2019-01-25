package com.example.android.agendabd;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface CompromissoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert (Compromisso compromisso);

    @Query("DELETE FROM compromisso_table")
    void deleteAll();

    @Query("DELETE FROM compromisso_table WHERE mComp = :compromissoName")
    void deleteCompromisso(String compromissoName);

    @Query("SELECT * from compromisso_table ORDER BY mComp ASC")
    LiveData<List<Compromisso>> getAllCompromissos();

   /* @Query("UPDATE compromisso_table SET mComp = :novoComp, mDescricao = :novoDesc, mData = :novaData WHERE ID = :id")
    void updateCompromisso(String novoComp,String novoDesc, String novaData,int id);*/

    /* @Query("UPDATE compromisso_table SET mComp = comp.get, mDescricao = :novoDesc, mData = :novaData WHERE ID = :id")
    void updateCompromisso(Compromisso comp);*/

    @Update
    int updateCompromisso(Compromisso... comp);



}
