package com.iat.stepdefs.utils

import org.monte.media.Format
import org.monte.media.FormatKeys
import org.monte.media.VideoFormatKeys
import org.monte.media.math.Rational
import org.monte.screenrecorder.ScreenRecorder

import java.awt.*

class VideoRecorder {

    private static VideoRecorder videoRecorder
    private ScreenRecorder screenRecorder
    private boolean videosRemoved = false
    private static directory = "build//reports//videoDirectory"

    VideoRecorder() {
        //Create a instance of GraphicsConfiguration to get the Graphics configuration
        //of the Screen. This is needed for ScreenRecorder class.
        GraphicsConfiguration gc = GraphicsEnvironment//
                .getLocalGraphicsEnvironment()//
                .getDefaultScreenDevice()//
                .getDefaultConfiguration()

        //Create a instance of ScreenRecorder with the required configurations
        screenRecorder = new ScreenRecorder(gc, gc.getBounds(),
                new Format(FormatKeys.MediaTypeKey, FormatKeys.MediaType.FILE, FormatKeys.MimeTypeKey, FormatKeys.MIME_AVI),
                new Format(FormatKeys.MediaTypeKey, FormatKeys.MediaType.VIDEO, FormatKeys.EncodingKey, VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        VideoFormatKeys.CompressorNameKey, VideoFormatKeys.ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        VideoFormatKeys.DepthKey, 24, FormatKeys.FrameRateKey, Rational.valueOf(15),
                        VideoFormatKeys.QualityKey, 1.0f,
                        FormatKeys.KeyFrameIntervalKey, 15 * 60),
                new Format(FormatKeys.MediaTypeKey, FormatKeys.MediaType.VIDEO, FormatKeys.EncodingKey, "black",
                        FormatKeys.FrameRateKey, Rational.valueOf(30)),
                null, new File(directory))
    }

    static VideoRecorder getInstance() {

        if (videoRecorder == null)
            videoRecorder = new VideoRecorder()
        videoRecorder
    }

    def startRecording() {
        screenRecorder.start()
    }

    def stopRecording() {
        screenRecorder.stop()
    }

    def removePreviousTestExecutionVideos() {
        if (!isVideosRemoved()) {
            setVideosRemoved(true)
            for (File f : new File(directory).listFiles()) {
                if (f.getName().startsWith("ScreenRecording"))
                    f.delete()
            }
        }
    }

    def displayCreatedMovieFiles() {
        ArrayList<File> createdMovieFiles = screenRecorder.getCreatedMovieFiles()
        for (File movie : createdMovieFiles)
            System.out.println("New movie created: " + movie.getAbsolutePath())
    }

    void deleteLatestCreatedMovieFile() {
        File[] files = new File("build//reports//videoDirectory").listFiles()
        files.last().delete()
    }

    def isVideosRemoved() {
        videosRemoved
    }

    def setVideosRemoved(videosRemoved) {
        this.videosRemoved = videosRemoved
    }

}