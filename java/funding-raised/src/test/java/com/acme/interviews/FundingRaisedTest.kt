package com.acme.interviews

import com.acme.interviews.FundingRaised.findBy
import com.acme.interviews.FundingRaised.where
import junit.framework.Assert
import junit.framework.Test
import junit.framework.TestCase
import junit.framework.TestSuite
import java.io.IOException

/**
 * Unit test for simple App.
 */
class FundingRaisedTest

/**
 * Create the test case
 * 
 * @param testName name of the test case
 */
    (testName: String?) : TestCase(testName) {
    /**
     * Rigourous Test :-)
     */
    fun testWhereGivenCompany() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("company_name", "Facebook")
            Assert.assertEquals(where(options).size, 7)
        } catch (e: IOException) {
            print(e.message)
            print("error")
        }
    }

    fun testWhereGivenCity() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("city", "Tempe")
            Assert.assertEquals(where(options).size, 3)
        } catch (e: IOException) {
            print(e.message)
            print("error")
        }
    }

    fun testWhereGivenState() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("state", "CA")
            Assert.assertEquals(where(options).size, 873)
        } catch (e: IOException) {
            print(e.message)
            print("error")
        }
    }

    fun testWhereGivenRound() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("round", "a")
            Assert.assertEquals(where(options).size, 582)
        } catch (e: IOException) {
            print(e.message)
            print("error")
        }
    }

    fun testMultipleOptions() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("round", "a")
            options.put("company_name", "Facebook")
            Assert.assertEquals(where(options).size, 1)
        } catch (e: IOException) {
            print(e.message)
            print("error")
        }
    }

    fun testWhereNotExists() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("company_name", "NotFacebook")
            Assert.assertEquals(where(options).size, 0)
        } catch (e: IOException) {
            print(e.message)
            print("error")
        }
    }

    fun Funding.valueFor(key:String) = details.find { it.name == key }!!.value

    fun testWhereCorrectKeys() {
        val options: MutableMap<String?, String?> = HashMap<String?, String?>()
        options.put("company_name", "Facebook")
        try {
            val funding: Funding = where(options)[0]
            assertEquals(funding.valueFor("permalink"), "facebook");
            assertEquals(funding.valueFor("company_name"), "Facebook");
            assertEquals(funding.valueFor("number_employees"), "450");
            assertEquals(funding.valueFor("category"), "web");
            assertEquals(funding.valueFor("city"), "Palo Alto");
            assertEquals(funding.valueFor("state"), "CA");
            assertEquals(funding.valueFor("funded_date"), "1-Sep-04");
            assertEquals(funding.valueFor("raised_amount"), "500000");
            assertEquals(funding.valueFor("round"), "angel");

        } catch (e: IOException) {
            print(e.message);
            print("error");
        }

    }

    fun testFindByGivenCompanyName() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("company_name", "Facebook")
            val row = findBy(options).first()

            Assert.assertEquals(row.valueFor("permalink"), "facebook")
//            Assert.assertEquals(row.valueFor("permalink"), "facebook")
//            Assert.assertEquals(row.valueFor("permalink"), "facebook")
//            Assert.assertEquals(row.get("permalink"), "facebook")
            /*assertEquals(row.get("company_name"), "Facebook")
            assertEquals(row.get("number_employees"), "450")
            assertEquals(row.get("category"), "web")
            assertEquals(row.get("city"), "Palo Alto")
            assertEquals(row.get("state"), "CA")
            assertEquals(row.get("funded_date"), "1-Sep-04")
            assertEquals(row.get("raised_amount"), "500000")
            assertEquals(row.get("round"), "angel")*/
        } catch (e: IOException) {
            print(e.message)
            print("error")
        } catch (e: NoSuchEntryException) {
            print(e.message)
            print("error")
        }
    }

    fun testFindByGivenState() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("state", "CA")
            val row = findBy(options).first()

            assertEquals(row.valueFor("permalink"), "digg")
            assertEquals(row.valueFor("company_name"), "Digg")
            assertEquals(row.valueFor("number_employees"), "60")
            assertEquals(row.valueFor("category"), "web")
            assertEquals(row.valueFor("city"), "San Francisco")
            assertEquals(row.valueFor("state"), "CA")
            assertEquals(row.valueFor("funded_date"), "1-Dec-06")
            assertEquals(row.valueFor("raised_amount"), "8500000")
            assertEquals(row.valueFor("round"), "b")
        } catch (e: IOException) {
            print(e.message)
            print("error")
        } catch (e: NoSuchEntryException) {
            print(e.message)
            print("error")
        }
    }
        fun testFindByMultipleOptions() {
            try {
                val options: MutableMap<String?, String?> = HashMap<String?, String?>()
                options.put("company_name", "Facebook")
                options.put("round", "c")
                val row = findBy(options).first()

                assertEquals(row.valueFor("permalink"), "facebook")
                assertEquals(row.valueFor("company_name"), "Facebook")
                assertEquals(row.valueFor("number_employees"), "450")
                assertEquals(row.valueFor("category"), "web")
                assertEquals(row.valueFor("city"), "Palo Alto")
                assertEquals(row.valueFor("state"), "CA")
                assertEquals(row.valueFor("funded_date"), "1-Oct-07")
                assertEquals(row.valueFor("raised_amount"), "300000000")
                assertEquals(row.valueFor("round"), "c")
            } catch (e: IOException) {
                print(e.message)
                print("error")
            } catch (e: NoSuchEntryException) {
                print(e.message)
                print("error")
            }
        }

    fun testFindByNotExists() {
        try {
            val options: MutableMap<String?, String?> = HashMap<String?, String?>()
            options.put("company_name", "NotFacebook")
            options.put("round", "c")
            val row = findBy(options)
            fail("findBy should throw exception")
        } catch (e: IOException) {
            print(e.message)
            print("error")
        } catch (e: NoSuchEntryException) {
        }
    }

    companion object {
        /**
         * @return the suite of tests being tested
         */
        fun suite(): Test {
            return TestSuite(FundingRaisedTest::class.java)
        }
    }
}
