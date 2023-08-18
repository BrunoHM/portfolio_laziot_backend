package com.senai.laziot.event.DTO;

import com.senai.laziot.event.EventEntity;
import com.senai.laziot.event.translator.EventFilterDTOTranslator;
import lombok.*;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class EventFilterDTO {

    private Long id;

    private List<String> actions;

    private String date;
    private String dateEnd;

    private String time;
    private String timeEnd;

    private List<Integer> manual;
    private List<Integer> executed;

    public EventFilterDTO(EventEntity eventEntity){
        this.id = Optional.ofNullable(eventEntity.getId()).orElse(0L);
        this.date = Optional.ofNullable(eventEntity.getDate()).orElse("");
        this.time = Optional.ofNullable(eventEntity.getTime()).orElse("");

        this.manual = Optional.ofNullable(eventEntity.isManual()).map(obj -> obj ? List.of(1) : List.of(0)).orElse(List.of(0));
        this.executed = Optional.ofNullable(eventEntity.isExecuted()).map(obj -> obj ? List.of(1) : List.of(0)).orElse(List.of(0));

        this.actions = Optional.ofNullable(eventEntity.getFkIdAction()).map(obj -> List.of(obj.getDescription())).orElse(List.of(""));
    }

}
