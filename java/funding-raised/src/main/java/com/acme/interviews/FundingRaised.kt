package com.acme.interviews

import com.opencsv.CSVReader
import com.opencsv.exceptions.CsvValidationException
import java.io.FileReader
import java.io.IOException

object FundingRaised {
    @JvmStatic
    @Throws(IOException::class)
    fun where(options: MutableMap<String?, String?>): MutableList<MutableMap<String?, String?>?> {
        var csvData: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()
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

        if (options.containsKey("company_name")) {
            val results: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()

            for (i in csvData.indices) {
                if (csvData.get(i)!![1] == options.get("company_name")) {
                    results.add(csvData.get(i))
                }
            }
            csvData = results
        }

        if (options.containsKey("city")) {
            val results: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()

            for (i in csvData.indices) {
                if (csvData.get(i)!![4] == options.get("city")) {
                    results.add(csvData.get(i))
                }
            }
            csvData = results
        }

        if (options.containsKey("state")) {
            val results: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()

            for (i in csvData.indices) {
                if (csvData.get(i)!![5] == options.get("state")) {
                    results.add(csvData.get(i))
                }
            }
            csvData = results
        }

        if (options.containsKey("round")) {
            val results: MutableList<Array<String?>?> = ArrayList<Array<String?>?>()

            for (i in csvData.indices) {
                if (csvData.get(i)!![9] == options.get("round")) {
                    results.add(csvData.get(i))
                }
            }
            csvData = results
        }

        val output: MutableList<MutableMap<String?, String?>?> = ArrayList<MutableMap<String?, String?>?>()

        for (i in csvData.indices) {
            val mapped: MutableMap<String?, String?> = HashMap<String?, String?>()
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
            output.add(mapped)
        }

        return output
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

