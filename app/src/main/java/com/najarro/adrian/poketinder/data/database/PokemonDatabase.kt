package com.najarro.adrian.poketinder.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.najarro.adrian.poketinder.data.database.dao.PokemonDao
import com.najarro.adrian.poketinder.data.database.entities.MyPokemonEntity


@Database(entities = [MyPokemonEntity::class], version=1)
abstract class PokemonDatabase: RoomDatabase() {
    abstract fun getPokemonDao(): PokemonDao
}