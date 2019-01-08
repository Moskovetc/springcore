package org.shop.annotation

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Retention(RetentionPolicy.RUNTIME)
@interface InjectRandomInt {
    int min()
    int max()
}