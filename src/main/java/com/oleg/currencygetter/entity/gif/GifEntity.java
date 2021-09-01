package com.oleg.currencygetter.entity.gif;

import lombok.*;
import lombok.experimental.Accessors;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@ToString
public class GifEntity {
    private Images images;
}
