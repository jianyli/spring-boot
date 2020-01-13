package com.li.support.util;

import com.li.support.enums.ErrorCodeEnum;
import com.li.support.exception.ServiceException;

import java.util.function.Consumer;

public class ExceptionUtil {
    public static <T> Consumer<T> consumerWrapper(Consumer<T> consumer, ErrorCodeEnum errorCodeEnum, String message) {
        return i->{
          try {
              consumer.accept(i);
          } catch (Exception e) {
              if (errorCodeEnum != null) {
                  throw new ServiceException(errorCodeEnum);
              } else {
                  throw new ServiceException(message);
              }

          }
        };
    }
}
