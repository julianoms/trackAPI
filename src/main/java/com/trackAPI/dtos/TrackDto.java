package com.trackAPI.dtos;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class TrackDto {

    @NonNull
    private String artist;

    @NonNull
    private String name;

    @NonNull
    private String albumName;

    private String track_number;

    private String uri;
}
