package com.iat.pages.home.modules

import geb.Module

import static org.hamcrest.MatcherAssert.assertThat
import static org.hamcrest.Matchers.is

class CarouselBannerModule extends Module {

    static content = {
        root(wait: true, required: true) { $("block-with-carousel-banner") }
        leftArrow { root.$(".Carousel-leftClickableArea") }
        rightArrow { root.$(".Carousel-rightClickableArea") }
        dots { root.$(".Carousel-dots-dot") }
        banners { root.$(".Carousel-images") }
    }

    void selectBanner(int index) {
        dots[index].click()
        waitFor { whichDotIsBigger() == index }
    }

    int selectRandomBanner(int range) {
        int index = new Random().nextInt(range)
        selectBanner(index)
        return index
    }

    int whichBannerIsActive() {
        return banners.findIndexOf {
            it.has(".Carousel-images--animateImageLeft, .Carousel-images--animateImageRight")
        }
    }

    int whichDotIsBigger() {
        return dots.findIndexOf { it.hasClass("Carousel-dots-dot--bigDot") }
    }

    @Override
    boolean isDisplayed() {
        return leftArrow.isDisplayed() && rightArrow.isDisplayed() && dots.size() > 0 && banners.size() > 0
    }

    @Override
    int size() {
        int howManyDots = dots.size()
        assertThat("Amount of dots and banners is not matching!", banners.size(), is(howManyDots))
        return howManyDots
    }
}