mod day1;
mod day2;
use crate::day1::*;
use crate::day2::*;

fn main() {
    println!("Hello, world!");
    println!("Day 1-1: {}", day1_1("inputs/day1.txt"));
    println!("Day 1-2: {}", day1_2("inputs/day1.txt"));
    println!("Day 2-1: {}", day2_1("inputs/day2.txt"));
    println!("Day 2-2: {}", day2_2("inputs/day2.txt"));
}
