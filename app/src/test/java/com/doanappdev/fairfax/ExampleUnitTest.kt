package com.doanappdev.fairfax

import com.doanappdev.fairfax.base.ItemView
import com.doanappdev.fairfax.data.models.ITEM_ASSET
import com.doanappdev.fairfax.data.models.Image
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private var dates = listOf<Date>()

    /**
     * Initiate list of dates
     */
    @Before
    fun setUp() {
        dates = getListOfDates()
    }

    /**
     * This test is used to confirm a given date is after another
     * date
     * 'isAfter' should be true
     */
    @Test
    fun shouldBeTrue_Date1IsAfterDate2_GivenTwoDates() {
        // check if first date in list is after second date in list
        var date1 = dates[0]
        var date2 = dates[1]
        var isAfter = date1.after(date2)
        System.out.println("date1 is after date2 : $isAfter")
        assertTrue(isAfter)

        // check if first date in list is after third date in list
        date2 = dates[2]
        isAfter = date1.after(date2)
        System.out.println("date1 is after date2 : $isAfter")
        assertTrue(isAfter)
    }

    /**
     * This test checks date1 is not 'after' date2
     * 'isAfter' should be false
     */
    @Test
    fun shouldBeFalse_Date1IsAfterDate2_GivenTwoDates() {
        // check if date1 is after date2, this should display false
        var date1 = dates[15]
        var date2 = dates[1]
        var isAfter = date1.after(date2)
        System.out.println("date1 is after date2 : $isAfter")
        assertFalse(isAfter)

        date1 = dates[1]
        date2 = dates[0]
        isAfter = date1.after(date2)
        System.out.println("date1 is after date2 : $isAfter")
        assertFalse(isAfter)
    }

    /**
     * This test verifies a list of dates is sorted in ascending
     * and descending order
     */
    @Test
    fun shouldSortList_GivenListOfDates() {
        val sortedDatesAscending = dates.sorted()
        sortedDatesAscending.forEach {
            System.out.println(it)
        }

        System.out.println("**********************")

        val sortedDatesDescending = sortedDatesAscending.reversed()
        sortedDatesDescending.forEach {
            System.out.println(it)
        }
    }

    /**
     * This test is used to verify that a given list of NewsItems
     * can be sorted.
     * The requirement is to display news items from latest first, so this
     * is in descending order.
     */
    @Test
    fun shouldSortNewsItemsByDate_GivenListOfNewsItems() {
        val sortedListOfAssets = mutableListOf<NewsItemMock>()

        getNewsItems()
                .sortedWith(compareByDescending { it.asset.date })
                .forEach { sortedListOfAssets.add(it) }

        // print list to verify dates are sorted
        sortedListOfAssets.forEach {
            System.out.println(it.asset.date)
        }

        val date1 = sortedListOfAssets[0].asset.date
        val date2 = sortedListOfAssets[1].asset.date
        val date3 = sortedListOfAssets[3].asset.date

        // verify that the first date in list is after second date
        assertTrue(date1!!.after(date2))

        // verify that the last date in list is not after first date
        assertFalse(date3!!.after(date1))
    }

    @Test
    fun shouldFindSmallestImage_GivenListOfImages() {
        val newsItems = getNewsItems()

        // give list of newsItems,
        newsItems.forEach {
            // then call findSmallestImage on each asset
            it.asset.findSmallestImage()
        }

        // verify smallestImage is actually smallest
        newsItems.forEach {
            val width = it.asset.smallestImage?.width
            val height = it.asset.smallestImage?.height
            System.out.println("width : $width")
            System.out.println("height : $height")
            assertEquals(375, width)
            assertEquals(250, height)
        }

    }

    /**
     * Helper method to return a list of mock NewsItems, which is used to
     * sort
     */
    private fun getNewsItems() : List<NewsItemMock> {
        return listOf(
                NewsItemMock(getAsset(Date(1523023200000), getImages())),
                NewsItemMock(getAsset(Date(1522937700000), getImages())),
                NewsItemMock(getAsset(Date(1522933200000), getImages())),
                NewsItemMock(getAsset(Date(1522984500000), getImages()))
        )
    }


    /**
     * Helper method to create a mock Asset object with different Date
     * we only need to test is we can sort the list by date so we don't
     * need to update the url, headline and other text.
     */
    private fun getAsset(date: Date, relatedImages: List<ImageMock>) : AssetMock {
        val asset = AssetMock(
                1029525606,
                "http://www.afr.com/news/malcolm-turnbulls-power-struggles-reflect-uneasy-leadership-detente-20180404-h0yb1i",
                "Malcolm Turnbull's power struggles reflect uneasy detente",
                "A messy proxy war over the future of coal in Australia's power supply has slammed into a poll anniversary the Prime Minister would rather forget.",
                "Tom McIlroy",
                relatedImages,
                0
                )
        asset.date = date
        return asset
    }

    /**
     * Helper method to get list of dates to test comparing whether date1 is after date2
     */
    private fun getListOfDates() : List<Date> {
        return listOf(
                Date(1523023200000), // Sat Apr 07 00:00:00 GMT+10:00 2018
                Date(1522937700000), // Fri Apr 06 00:15:00 GMT+10:00 2018
                Date(1522994072912), // Fri Apr 06 15:54:32 GMT+10:00 2018
                Date(1523019600000), // Fri Apr 06 23:00:00 GMT+10:00 2018
                Date(1522999947078), // Fri Apr 06 17:32:27 GMT+10:00 2018
                Date(1523056381207), // Sat Apr 07 09:13:01 GMT+10:00 2018
                Date(1522979224603), // Fri Apr 06 11:47:04 GMT+10:00 2018
                Date(1522971900000), // Fri Apr 06 09:45:00 GMT+10:00 2018
                Date(1523019600000), // Fri Apr 06 23:00:00 GMT+10:00 2018
                Date(1523002368735), // Fri Apr 06 18:12:48 GMT+10:00 2018
                Date(1522984500000), // Fri Apr 06 13:15:00 GMT+10:00 2018
                Date(1523000546690), // Fri Apr 06 17:42:26 GMT+10:00 2018
                Date(1522958731232), // Fri Apr 06 06:05:31 GMT+10:00 2018
                Date(1522933200000), // Thu Apr 05 23:00:00 GMT+10:00 2018
                Date(1522851300000), // Thu Apr 05 00:15:00 GMT+10:00 2018
                Date(1522933200000)  // Thu Apr 05 23:00:00 GMT+10:00 2018
        )
    }

    /**
     * Helper method to get list of images
     */
    private fun getImages() : List<ImageMock> {
        return listOf(
                ImageMock(1536, 1010),
                ImageMock(640, 0),
                ImageMock(640, 0),
                ImageMock(640, 0),
                ImageMock(1536, 1010),
                ImageMock(1500, 844),
                ImageMock(375, 250),
                ImageMock(1174, 783),
                ImageMock(640, 0)
        )
    }
}

/**
 * These objects are used as 'mock' objects so we do not need to add all the
 * fields in the real Asset object, the test is verifying a list of NewsItems can be
 * sorted by Date, so the object does not need all the other information, so it is
 * being mocked.
 * This was created to help reduce the amount of information needed to create
 * the Asset object. Kotlin data classes can not be created with fields missing
 */
data class AssetMock(
        val id: Int,
        val url: String,
        val headline: String,
        val theAbstract: String,
        val byLine: String,
        val relatedImages: List<ImageMock>,
        val timeStamp: Long
) {
    var date: Date? = null

    var smallestImage: ImageMock? = null


    /**
     * As this is the mock object we can easily copy this method to the
     * real implementation of Asset when this method is updated
     */
    fun findSmallestImage() {
        // set the minWidth & minHeight using first image in list
        smallestImage = relatedImages[0]
        var minWidth = relatedImages[0].width
        var minHeight = relatedImages[0].height

        // loop through list to check if width and height are smaller
        // if both are smaller we set the current image to smallestImage
        relatedImages.map {
            // some of the images have height 0,
            if (it.height != 0) {
                when((it.width <= minWidth && it.height <= minHeight)) {
                    true -> {
                        minWidth = it.width
                        minHeight = it.height
                        smallestImage = it
                    }
                }
            }
        }

    }
}

data class NewsItemMock(val asset: AssetMock) : ItemView {
    override fun getItemType() = ITEM_ASSET
}


/**
 * Mock image object which sets only the width and height
 * as these are the only values we need to check for image size
 */
data class ImageMock(val width: Int, val height: Int)
