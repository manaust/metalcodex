package be.satanica.metalcodex.data.database.daos;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

import io.reactivex.Completable;

abstract class BaseDao<T> {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(T item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(T[] items);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract void insert(List<T> items);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract Completable completableInsert(T item);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract Completable completableInsert(T[] items);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    public abstract Completable completableInsert(List<T> items);

    @Delete
    public abstract Completable delete(T item);
}
