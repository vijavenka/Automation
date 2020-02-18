package com.iat.stepdefs.utils

import org.monte.media.Format
import org.monte.media.math.Rational
import org.monte.screenrecorder.ScreenRecorder

import java.awt.*

import static org.monte.media.FormatKeys.*
import static org.monte.media.VideoFormatKeys.*

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
                new Format(MediaTypeKey, MediaType.FILE, MimeTypeKey, MIME_AVI),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        CompressorNameKey, ENCODING_AVI_TECHSMITH_SCREEN_CAPTURE,
                        DepthKey, (int) 24, FrameRateKey, Rational.valueOf(15),
                        QualityKey, 1.0f,
                        KeyFrameIntervalKey, (int) (15 * 60)),
                new Format(MediaTypeKey, MediaType.VIDEO, EncodingKey, "black",
                        FrameRateKey, Rational.valueOf(30)),
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
            for (File f : new File(directory).listFiles())
                if (f.getName().startsWith("ScreenRecording"))
                    f.delete()
        }
    }

    def displayCreatedMovieFiles() {
        ArrayList<File> createdMovieFiles = screenRecorder.getCreatedMovieFiles()
        for (File movie : createdMovieFiles)
            System.out.println("New movie created: " + movie.getAbsolutePath())
    }

    def deleteLatestCreatedMovieFile() {
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