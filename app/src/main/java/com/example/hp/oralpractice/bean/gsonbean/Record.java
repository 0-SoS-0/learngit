package com.example.hp.oralpractice.bean.gsonbean;

import java.io.Serializable;

/**
 * Created by HP on 2018/2/23.
 */

public class Record implements Serializable{
        public static Record record;
        private Record(){

        }
        public static Record getInstance() {
                if (record == null) {
                        synchronized (Record.class) {
                                if (record == null) {
                                        record = new Record();
                                }
                        }
                }
                return record;
        }
}
