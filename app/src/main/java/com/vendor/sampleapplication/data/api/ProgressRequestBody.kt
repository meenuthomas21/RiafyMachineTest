package com.alnaqel.api

import okhttp3.MediaType
import okhttp3.RequestBody
import okio.*
import java.io.IOException
import kotlin.jvm.Throws

class ProgressRequestBody(
    protected var mDelegate: RequestBody,
    protected var mListener: Listener
) : RequestBody() {
    protected var mCountingSink: CountingSink? = null
    override fun contentType(): MediaType? {
        return mDelegate.contentType()
    }

    override fun contentLength(): Long {
        try {
            return mDelegate.contentLength()
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return -1
    }

    @Throws(IOException::class)
    override fun writeTo(sink: BufferedSink) {
        mCountingSink = CountingSink(sink)
        val bufferedSink = mCountingSink!!.buffer()
        mDelegate.writeTo(bufferedSink)
        bufferedSink.flush()
    }

    protected inner class CountingSink(delegate: Sink?) : ForwardingSink(delegate!!) {
        private var bytesWritten: Long = 0
        @Throws(IOException::class)
        override fun write(source: Buffer, byteCount: Long) {
            super.write(source, byteCount)
            bytesWritten += byteCount
            mListener.onProgress((100f * bytesWritten / contentLength()).toInt())
        }
    }

    interface Listener {
        fun onProgress(progress: Int)
    }
}