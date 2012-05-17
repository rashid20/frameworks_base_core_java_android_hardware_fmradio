/*
 * Copyright (c) 2009, Code Aurora Forum. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *    * Redistributions of source code must retain the above copyright
 *      notice, this list of conditions and the following disclaimer.
 *    * Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *    * Neither the name of Code Aurora nor
 *      the names of its contributors may be used to endorse or promote
 *      products derived from this software without specific prior written
 *      permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NON-INFRINGEMENT ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
 * WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR
 * OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 * ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package android.hardware.fmradio;

import android.util.Log;


/**
 *
 * Class to be used when changing radio settings
 * @hide
 */
public class FmConfig {


    /**
    * FMConfigure FM Radio band setting for US/Europe
    */
    private static final int FM_US_BAND        =0;
    /**
    * FMConfigure FM Radio band setting for US/Europe
    */
    private static final int FM_EU_BAND        =1;
    /**
    * FMConfigure FM Radio band setting for Japan
    */
    private static final int FM_JAPAN_WIDE_BAND       =2;
    /**
    * FMConfigure FM Radio band setting for Japan-Wideband
    */
    private static final int FM_JAPAN_STANDARD_BAND   =3;
    /**
    * FMConfigure FM Radio band setting for "User defined" band
    */
    private static final int FM_USER_DEFINED_BAND     =4;

    //private static final int V4L2_CID_PRIVATE_BASE                 =0x8000000;
    //private static final int V4L2_CID_PRIVATE_TAVARUA_REGION       =V4L2_CID_PRIVATE_BASE + 7;
    //private static final int V4L2_CID_PRIVATE_TAVARUA_EMPHASIS     =V4L2_CID_PRIVATE_BASE + 12;
    //private static final int V4L2_CID_PRIVATE_TAVARUA_RDS_STD      =V4L2_CID_PRIVATE_BASE + 13;
    //private static final int V4L2_CID_PRIVATE_TAVARUA_SPACING      =V4L2_CID_PRIVATE_BASE + 14;

    /* V4l2 User & FM Controls, see videodev2.h */
    private static final int V4L2_CTRL_CLASS_USER = 0x980000; /* Old-style 'user' controls */
    private static final int V4L2_CID_BASE = V4L2_CTRL_CLASS_USER | 0x900;
    private static final int V4L2_CID_AUDIO_MUTE = V4L2_CID_BASE + 9;
    private static final int V4L2_CTRL_CLASS_FM_TX = 0x009b0000;	/* FM Modulator control class */
    private static final int V4L2_CID_FM_TX_CLASS_BASE = V4L2_CTRL_CLASS_FM_TX | 0x900;
    private static final int V4L2_CID_TUNE_PREEMPHASIS = V4L2_CID_FM_TX_CLASS_BASE + 112;
    /* 
     V4L2_PREEMPHASIS_DISABLED       = 0,
     V4L2_PREEMPHASIS_50_uS          = 1,
     V4L2_PREEMPHASIS_75_uS          = 2,
     */
    
    private static final String TAG = "FmConfig";



    private int mRadioBand;
    /**
     * FM pre-emphasis/de-emphasis
     *
     * Possible Values:
     *
     * FmTransceiver.FM_DE_EMP75,
     * FmTransceiver.FM_DE_EMP50
     */
    private int mEmphasis;
    /**
     * Channel spacing
     *
     * Possible Values:
     *
     * FmTransceiver.FM_CHSPACE_200_KHZ,
     * FmTransceiver.FM_CHSPACE_100_KHZ,
     * FmTransceiver.FM_CHSPACE_50_KHZ
     */
    private int mChSpacing;

    /**
     * RDS standard type
     *
     * Possible Values:
     *
     * FmTransceiver.FM_RDS_STD_RBDS,
     * FmTransceiver.FM_RDS_STD_RDS,
     * FmTransceiver.FM_RDS_STD_NONE
     */
    private int mRdsStd;

    /**
     * FM Frequency Band Lower Limit in KHz
     */
    private int mBandLowerLimit;
    /**
     * FM Frequency Band Upper Limit in KHz
     */
    private int mBandUpperLimit;

    public int getRadioBand(){
        return mRadioBand;
    }

    public void setRadioBand (int band){
        mRadioBand = band;
    }

    public int getEmphasis(){
        return mEmphasis;
    }

    public void setEmphasis (int emp){
        mEmphasis = emp;
    }

    public int getChSpacing (){
        return mChSpacing;
    }

    public void setChSpacing(int spacing) {
        mChSpacing = spacing;
    }

    public int getRdsStd () {
        return mRdsStd;
    }

    public void setRdsStd (int rdsStandard) {
        mRdsStd = rdsStandard;
    }

    public int getLowerLimit(){
        return mBandLowerLimit;
    }

    public void setLowerLimit(int lowLimit){
        mBandLowerLimit = lowLimit;
    }

    public int getUpperLimit(){
        return mBandUpperLimit;
    }

    public void setUpperLimit(int upLimit){
        mBandUpperLimit = upLimit;
    }

    /*
     * fmConfigure()
     * This method call v4l2 private controls to set regional settings for the
     * FM core
     */
    protected static boolean fmConfigure (int fd, FmConfig configSettings) {

        int re;

        Log.d(TAG, "In fmConfigure");

        //re = FmReceiverJNI.setControlNative (fd, V4L2_CID_PRIVATE_TAVARUA_EMPHASIS, configSettings.getEmphasis());
        //re = FmReceiverJNI.setControlNative (fd, V4L2_CID_PRIVATE_TAVARUA_RDS_STD, configSettings.getRdsStd() );
        //re = FmReceiverJNI.setControlNative (fd, V4L2_CID_PRIVATE_TAVARUA_SPACING, configSettings.getChSpacing() );
        if (configSettings.getEmphasis() == 0) {
            // 0 = disabled, 1 = 50us, 2 = 75us in videodev2.h;
            // 0 = 75us, 1 = 50us, in FM app settings 
            Log.d(TAG, "Set Emphasis to 75us:");
            re = FmReceiverJNI.setControlNative(fd, V4L2_CID_TUNE_PREEMPHASIS, 2);
        } else if (configSettings.getEmphasis() == 1) {
            Log.d(TAG, "Set Emphasis to 50us:");
            re = FmReceiverJNI.setControlNative(fd, V4L2_CID_TUNE_PREEMPHASIS, 1);
        } else {
            Log.d(TAG, "Set Emphasis to default (none)");
            re = FmReceiverJNI.setControlNative(fd, V4L2_CID_TUNE_PREEMPHASIS, 0);
        }
        // DEBUG
        re = FmReceiverJNI.getControlNative(fd, V4L2_CID_TUNE_PREEMPHASIS);
        // BAND RANGE
        re = FmReceiverJNI.setBandNative (fd, configSettings.getLowerLimit(), configSettings.getUpperLimit() );

        //re = FmReceiverJNI.setControlNative (fd, V4L2_CID_PRIVATE_TAVARUA_REGION, FM_USER_DEFINED_BAND );

        // setControlNative for V4L2_CID_PRIVATE_TAVARUA_REGION triggers the
        // config change.
        if (re < 0)
          return false;

        return true;
    }

}
