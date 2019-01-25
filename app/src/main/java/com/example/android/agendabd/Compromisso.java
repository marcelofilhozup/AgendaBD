package com.example.android.agendabd;


import android.arch.persistence.room.ColumnInfo;

import android.arch.persistence.room.Entity;

import android.arch.persistence.room.PrimaryKey;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

@Entity(tableName = "compromisso_table")
public class    Compromisso implements Parcelable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int ID;
    public String mComp ;
    public String mDescricao ;
    public String mData;

    public Compromisso ( String comp, String descricao, String data){

        this.mComp = comp;
        this.mDescricao = descricao;
        this.mData = data;
    }

    protected Compromisso(Parcel in) {

        ID = in.readInt();
        mComp = in.readString();
        mDescricao = in.readString();
        mData = in.readString();
    }

    public static final Parcelable.Creator<Compromisso> CREATOR = new Creator<Compromisso>() {
        @Override
        public Compromisso createFromParcel(Parcel in) {
            return new Compromisso(in);
        }

        @Override
        public Compromisso[] newArray(int size) {
            return new Compromisso[size];
        }
    };


    public String getCompromisso() {
        return this.mComp;
    }

    public void setCompromisso(String comp){

        this.mComp = comp;

    }

    public Integer getID() {
        return this.ID;
    }

    public void setID(int id){

        this.ID = id;

    }



    public String getDescricao() {
        return this.mDescricao;
    }

    public void setDescricao(String desc){

        this.mDescricao = desc;

    }

    public String getData() {
        return this.mData;
    }

    public void setData(String data){

        this.mData = data;

    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(ID);
        dest.writeString(mComp);
        dest.writeString(mDescricao);
        dest.writeString(mData);
    }
}
