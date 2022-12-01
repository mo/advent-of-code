#!/bin/bash
for FOOD_LIST in $(cat ../data/day01.txt | tr '\n' ',' | sed 's/,,/ /g'); do echo $(echo $FOOD_LIST | tr , '\n' | awk '{s+=$1} END {print s}') ; done | sort -rn | head -1
for FOOD_LIST in $(cat ../data/day01.txt | tr '\n' ',' | sed 's/,,/ /g'); do echo $(echo $FOOD_LIST | tr , '\n' | awk '{s+=$1} END {print s}') ; done | sort -rn | head -3 | awk '{s+=$1} END {print s}'