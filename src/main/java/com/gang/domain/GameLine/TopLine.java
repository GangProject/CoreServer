package com.gang.domain.GameLine;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Created by seungki on 2017-06-02.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TopLine implements Comparable<TopLine>{
    String line;
    int percent;
    public int compareTo(TopLine n) {
        // TODO Auto-generated method stub
        return percent <= n.percent ? -1 : 1;
    }

}
