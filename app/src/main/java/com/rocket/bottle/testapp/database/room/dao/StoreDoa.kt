package com.rocket.bottle.testapp.database.room.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.rocket.bottle.testapp.database.room.seed.Store

@Dao
interface StoreDoa {

    @Query("SELECT * FROM store")
    fun getAll(): List<Store>

    @Query("SELECT * FROM store WHERE storeID IN (:storeID)")
    fun loadAllByIds(storeID: Int): Store

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg store: Store)

}