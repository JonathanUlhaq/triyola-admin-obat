package com.example.adminobattriyola.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.adminobattriyola.models.Distributor
import com.example.adminobattriyola.models.PengajuanObat


data class DistributorWithObat(
    @Embedded val distributor: Distributor,
    @Relation(
        parentColumn = "id",
        entityColumn = "distributorId"
    )
    val list:List<PengajuanObat>
)
