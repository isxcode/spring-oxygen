package com.isxcode.oxygen.core.snowflake;

public class SnowflakeUtils {

    private static final int TIME_LEN = 41;
    private static final int DATA_LEN = 5;
    private static final int WORK_LEN = 5;
    private static final int SEQ_LEN = 12;

    private static final long START_TIME = 1420041600000L;
    private static long LAST_TIME_STAMP = -1L;
    private static final int TIME_LEFT_BIT = 64 - 1 - TIME_LEN;

    private static final long DATA_ID = 1;
    private static final long WORK_ID = 1;
    private static final int DATA_LEFT_BIT = TIME_LEFT_BIT - DATA_LEN;
    private static final int WORK_LEFT_BIT = DATA_LEFT_BIT - WORK_LEN;

    private static long LAST_SEQ = 0L;
    private static final long SEQ_MAX_NUM = ~(-1 << SEQ_LEN);

    public static long nextMillis(long lastMillis) {
        long now = System.currentTimeMillis();
        while (now <= lastMillis) {
            now = System.currentTimeMillis();
        }
        return now;
    }

    public synchronized static String getNextUuid() {
        long now = System.currentTimeMillis();
        if (now < LAST_TIME_STAMP) {
            throw new RuntimeException("sys error, too fast");
        }
        if (now == LAST_TIME_STAMP) {
            LAST_SEQ = (LAST_SEQ + 1) & SEQ_MAX_NUM;
            if (LAST_SEQ == 0) {
                now = nextMillis(LAST_TIME_STAMP);
            }
        } else {
            LAST_SEQ = 0;
        }
        LAST_TIME_STAMP = now;
        long uuidLong = ((now - START_TIME) << TIME_LEFT_BIT) | (DATA_ID << DATA_LEFT_BIT) | (WORK_ID << WORK_LEFT_BIT) | LAST_SEQ;
        return String.valueOf(uuidLong);
    }
}
