package com.acme.interviews

import com.opencsv.CSVReader
import java.io.FileReader
import java.io.IOException

object FundingRaised {
    @JvmStatic
    @Throws(IOException::class)
    fun where(options: MutableMap<String?, String?>): List<Funding> {

        val csvReader = CSVReader(FileReader("startup_funding.csv"))

        return csvReader.readAll().drop(1).matchingFundsFor(options)

    }

    @JvmStatic
    @Throws(IOException::class, NoSuchEntryException::class)
    fun findBy(options: MutableMap<String?, String?>): List<Funding> {
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


private fun List<Array<String?>?>.matchingFundsFor(
    options: MutableMap<String?, String?>
): List<Funding> {
    val funding: List<Funding> = map { it!!.rawDataToFunding() }

    val res: List<FundingDetail> = options.map { (column, value) ->
        FundingDetail( column!!, value!!)
    }

    return funding.filter { funding ->
        funding.details.containsAll(res)
    }

}



fun Array<String?>.rawDataToFunding(): Funding {
    // convert to enum?
 val fundingDetails =  listOf(FundingDetail("permalink",get(0)!!),
     FundingDetail("company_name", get(1)!!),
         FundingDetail("number_employees", get(2)!!),
         FundingDetail("category", get(3)!!),
         FundingDetail("city", get(4)!!),
         FundingDetail("state", get(5)!!),
         FundingDetail("funded_date", get(6)!!),
         FundingDetail("raised_amount", get(7)!!),
         FundingDetail("raised_currency", get(8)!!),
         FundingDetail("round", get(9)!!)
    )

    return Funding(fundingDetails)

}

data class Funding(val details: List<FundingDetail>)

data class FundingDetail(val name: String, val value: String)

enum class FundingDetailType(val detailName: String, val index: Int) {
    PERMALINK("permalink", 0),
    COMPANY_NAME("company_name", 1),
    NUMBER_EMPLOYEES("number_employees", 2),
    CATEGORY("category", 3),
    CITY("city", 4),
    STATE("state", 5),
    FUNDED_DATE("funded_date", 6),
    RAISED_AMOUNT("raised_amount", 7),
    RAISED_CURRENCY("raised_currency", 8),
    ROUND("round", 9)
}
