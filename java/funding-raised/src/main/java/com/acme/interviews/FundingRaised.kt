package com.acme.interviews

import com.opencsv.CSVReader
import java.io.FileReader
import java.io.IOException

object FundingRaised {
    @JvmStatic
    @Throws(IOException::class)
    fun where(options: MutableMap<String?, String?>): List<Funding> {
        /*  var csvData: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()
          CSVReader(FileReader("startup_funding.csv")).readAll().forEach {
              it.forEach { print(it) }
              println()
          }*/
        val csvReader = CSVReader(FileReader("startup_funding.csv"))

        return csvReader.readAll().drop(1).newDataFor(options)

    }

    @JvmStatic
    @Throws(IOException::class, NoSuchEntryException::class)
    fun findBy(options: MutableMap<String?, String?>): List<Funding> {//MutableMap<String?, String?> {
        return where(options).ifEmpty { throw NoSuchEntryException() }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("company_name", "Facebook")
            options.put("round", "a")
            print(where(options).size)
        } catch (e: IOException) {
            print(e.message)
            print("error")
        }
    }
}


private fun MutableList<Array<String?>?>.convertToMap(): List<MutableMap<String?, String?>?> {
    return map {
        it.toMap()
    }

}

private fun Array<String?>?.toMap(): MutableMap<String?, String?> {
    val mapped: MutableMap<String?, String?> = HashMap<String?, String?>()
    mapped.put("permalink", this!![0])
    mapped.put("company_name", this!![1])
    mapped.put("number_employees", this!![2])
    mapped.put("category", this!![3])
    mapped.put("city", this!![4])
    mapped.put("state", this!![5])
    mapped.put("funded_date", this!![6])
    mapped.put("raised_amount", this!![7])
    mapped.put("raised_currency", this!![8])
    mapped.put("round", this!![9])
    return mapped
}


// convert array to columns
// Fund (list of columns)

private fun List<Array<String?>?>.newDataFor(
    options: MutableMap<String?, String?>, // user data to look for
): List<Funding> {
    val listThatHaveListOfColumns: List<List<FundingDetail>> = map { it!!.rawDataToColumns() }
    val listOfFunding: List<Funding> = listThatHaveListOfColumns.map { Funding(it) }

    val res: List<FundingDetail> = options.map { (column, value) ->
        FundingDetail(name = column!!, value = value!!)
    }

    val funding = listOfFunding.filter { funding ->
        funding.details.containsAll(res)
    }

    return funding

}
/**
 * [lifelock, LifeLock, , web, Tempe, AZ, 1-May-07, 6850000, USD, b]
 * [lifelock, LifeLock, , web, Tempe, AZ, 1-Oct-06, 6000000, USD, a]
 *
 *  table with header
 *  stock (....)
 *  stock (....)
 *
 */

// if map of details contains the column we're looking for then iterate over the list
// we need all data in the map to be correct


data class Column(val name: String, val index: Int)

data class Funding(val details: List<FundingDetail>)

data class FundingDetail(val name: String, val value: String)

fun Array<String?>.rawDataToColumns(): List<FundingDetail> {
    val permalink = get(0)
    val company_name = get(1)
    val number_employees = get(2)
    val category = get(3)
    val city = get(4)
    val state = get(5)
    val funded_date = get(6)
    val raised_amount = get(7)
    val raised_currency = get(8)
    val round = get(9)

    return buildList {
        add(FundingDetail("permalink", permalink!!))
        add(FundingDetail("company_name", company_name!!))
        add(FundingDetail("number_employees", number_employees!!))
        add(FundingDetail("category", category!!))
        add(FundingDetail("city", city!!))
        add(FundingDetail("state", state!!))
        add(FundingDetail("funded_date", funded_date!!))
        add(FundingDetail("raised_amount", raised_amount!!))
        add(FundingDetail("raised_currency", raised_currency!!))
        add(FundingDetail("round", round!!))
    }

}


val columns = listOf(
    Column("permalink", 0),
    Column("company_name", 1),
    Column("number_employees", 2),
    Column("category", 3),
    Column("city", 4),
    Column("state", 5),
    Column("funded_date", 6),
    Column("raised_amount", 7),
    Column("raised_currency", 8),
    Column("round", 9),
)

