package com.acme.interviews

import com.opencsv.CSVReader
import com.opencsv.exceptions.CsvValidationException
import java.io.FileReader
import java.io.IOException
import kotlin.collections.buildMap

object FundingRaised {
    @JvmStatic
    @Throws(IOException::class)
    fun where(options: MutableMap<String?, String?>): List<Funding>{//List<MutableMap<String?, String?>?> {
        var csvData: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()
        CSVReader(FileReader("startup_funding.csv")).readAll().forEach {
            it.forEach { print(it) }
            println()
        }
        val reader: CSVReader = CSVReader(FileReader("startup_funding.csv"))
        var row: Array<String?>? = null
        // TODO convert to readlAll()
        //val row1: List<Array<out String?>?>? = reader.readAll()


        while (true) {
            try {
                if ((reader.readNext().also { row = it }) == null) break
            } catch (e: CsvValidationException) {
                throw RuntimeException(e)
            }
            csvData.add(row)
        }

        reader.close()

        csvData.removeAt(0)

        return newDataFor(options, csvData)

        // one time iterate over the map
    /*    csvData = dataFor(options, csvData, Column("company_name", 1))
        csvData = dataFor(options, csvData, Column("city", index = 4))
        csvData = dataFor(options, csvData, Column("state", index = 5))
        csvData = dataFor(options, csvData, Column("round", index = 9))

        csvData.take(2).forEach { println(it.contentToString()) }

        return csvData.convertToMap()*/
    }

    @JvmStatic
    @Throws(IOException::class, NoSuchEntryException::class)
    fun findBy(options: MutableMap<String?, String?>): MutableMap<String?, String?> {
        val csvData: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()
        val reader = CSVReader(FileReader("startup_funding.csv"))
        var row: Array<String?>? = null

        while (true) {
            try {
                if ((reader.readNext().also { row = it }) == null) break
            } catch (e: CsvValidationException) {
                throw RuntimeException(e)
            }
            csvData.add(row)
        }

        reader.close()
        csvData.removeAt(0)
        val mapped: MutableMap<String?, String?> = HashMap<String?, String?>()

        for (i in csvData.indices) {
            if (options.containsKey("company_name")) {
                if (csvData.get(i)!![1] == options.get("company_name")) {
                    mapped.put("permalink", csvData.get(i)!![0])
                    mapped.put("company_name", csvData.get(i)!![1])
                    mapped.put("number_employees", csvData.get(i)!![2])
                    mapped.put("category", csvData.get(i)!![3])
                    mapped.put("city", csvData.get(i)!![4])
                    mapped.put("state", csvData.get(i)!![5])
                    mapped.put("funded_date", csvData.get(i)!![6])
                    mapped.put("raised_amount", csvData.get(i)!![7])
                    mapped.put("raised_currency", csvData.get(i)!![8])
                    mapped.put("round", csvData.get(i)!![9])
                } else {
                    continue
                }
            }

            if (options.containsKey("city")) {
                if (csvData.get(i)!![4] == options.get("city")) {
                    mapped.put("permalink", csvData.get(i)!![0])
                    mapped.put("company_name", csvData.get(i)!![1])
                    mapped.put("number_employees", csvData.get(i)!![2])
                    mapped.put("category", csvData.get(i)!![3])
                    mapped.put("city", csvData.get(i)!![4])
                    mapped.put("state", csvData.get(i)!![5])
                    mapped.put("funded_date", csvData.get(i)!![6])
                    mapped.put("raised_amount", csvData.get(i)!![7])
                    mapped.put("raised_currency", csvData.get(i)!![8])
                    mapped.put("round", csvData.get(i)!![9])
                } else {
                    continue
                }
            }

            if (options.containsKey("state")) {
                if (csvData.get(i)!![5] == options.get("state")) {
                    mapped.put("permalink", csvData.get(i)!![0])
                    mapped.put("company_name", csvData.get(i)!![1])
                    mapped.put("number_employees", csvData.get(i)!![2])
                    mapped.put("category", csvData.get(i)!![3])
                    mapped.put("city", csvData.get(i)!![4])
                    mapped.put("state", csvData.get(i)!![5])
                    mapped.put("funded_date", csvData.get(i)!![6])
                    mapped.put("raised_amount", csvData.get(i)!![7])
                    mapped.put("raised_currency", csvData.get(i)!![8])
                    mapped.put("round", csvData.get(i)!![9])
                } else {
                    continue
                }
            }

            if (options.containsKey("round")) {
                if (csvData.get(i)!![9] == options.get("round")) {
                    mapped.put("permalink", csvData.get(i)!![0])
                    mapped.put("company_name", csvData.get(i)!![1])
                    mapped.put("number_employees", csvData.get(i)!![2])
                    mapped.put("category", csvData.get(i)!![3])
                    mapped.put("city", csvData.get(i)!![4])
                    mapped.put("state", csvData.get(i)!![5])
                    mapped.put("funded_date", csvData.get(i)!![6])
                    mapped.put("raised_amount", csvData.get(i)!![7])
                    mapped.put("raised_currency", csvData.get(i)!![8])
                    mapped.put("round", csvData.get(i)!![9])
                } else {
                    continue
                }
            }

            return mapped
        }

        throw NoSuchEntryException()
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
private fun newDataFor(
    options: MutableMap<String?, String?>, // user data to look for
    csvData: MutableList<Array<String?>?>,
//    column: Column
): List<Funding> {
    val listThatHaveListOfColumns: List<List<NewColumn>> = csvData.map { it!!.rawDataToColumns() }
    val listOfFunding: List<Funding> = listThatHaveListOfColumns.map { Funding(it) }

    val res: List<NewColumn> = options.map { (column, value) ->
        NewColumn(name = column!!, value = value!!)
    }

    val funding =  listOfFunding.filter { funding ->
        funding.details.containsAll(res)
    }

    return funding

}


// if map of details contains the column we're looking for then iterate over the list
// we need all data in the map to be correct


private fun dataFor(
    options: MutableMap<String?, String?>,
    csvData: MutableList<Array<String?>?>,
    column: Column
): MutableList<Array<String?>?> {
    // if map of details contains the column we're looking for then iterate over the list
    // we need all data in the map to be correct

    var csvData1 = csvData
    if (options.containsKey(column.name)) {
        val results: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()

        for (i in csvData1.indices) {
            // i is array of strings which represent one stock values
            if (csvData1[i]!![column.index] == options[column.name]) {
                results.add(csvData1[i])
            }
        }
        csvData1 = results
    }
    return csvData1
}

data class Column(val name: String, val index: Int)

data class NewColumn(val name: String, val value: String)

/**
 * [lifelock, LifeLock, , web, Tempe, AZ, 1-May-07, 6850000, USD, b]
 * [lifelock, LifeLock, , web, Tempe, AZ, 1-Oct-06, 6000000, USD, a]
 *
 *  table with header
 *  stock (....)
 *  stock (....)
 *
 */

data class Funding(val details: List<NewColumn>)

fun Array<String?>.rawDataToColumns(): List<NewColumn> {
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
        add(NewColumn("permalink", permalink!!))
        add(NewColumn("company_name", company_name!!))
        add(NewColumn("number_employees", number_employees!!))
        add(NewColumn("category", category!!))
        add(NewColumn("city", city!!))
        add(NewColumn("state", state!!))
        add(NewColumn("funded_date", funded_date!!))
        add(NewColumn("raised_amount", raised_amount!!))
        add(NewColumn("raised_currency", raised_currency!!))
        add(NewColumn("round", round!!))
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

fun bla (){
    columns.map {  }
}