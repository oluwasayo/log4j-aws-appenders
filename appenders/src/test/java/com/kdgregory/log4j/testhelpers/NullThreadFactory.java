// Copyright (c) Keith D Gregory, all rights reserved
package com.kdgregory.log4j.testhelpers;

import java.lang.Thread.UncaughtExceptionHandler;

import com.kdgregory.log4j.aws.internal.shared.LogWriter;
import com.kdgregory.log4j.aws.internal.shared.ThreadFactory;


/**
 *  A {@link ThreadFactory} used for testing: it doesn't invoke the writer at all.
 */
public class NullThreadFactory implements ThreadFactory
{
    @Override
    public void startLoggingThread(LogWriter writer, UncaughtExceptionHandler exceptionHandler)
    {
        // nothing here
    }
}
