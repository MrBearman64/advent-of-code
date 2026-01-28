use std::fs;
use std::collections::HashMap;

pub fn day1_1(file_path: &str) -> i32{
    let content = fs::read_to_string(file_path).expect("Could not find input file!");
    let mut list_a = vec![];
    let mut list_b = vec![];
    for line in content.lines() {
        let vals: Vec<_> = line.split_whitespace().map(|x|x.parse::<i32>().unwrap()).collect();
        list_a.push(vals[0]);
        list_b.push(vals[1]);
    }
    list_a.sort();
    list_b.sort();
    list_a.iter().zip(list_b.iter()).map(|(x,y)| (x-y).abs()).sum()
}

pub fn day1_2(file_path: &str) -> i32 {
    let content = fs::read_to_string(file_path).expect("Could not find input file!");
    let mut hmap_a = HashMap::new();
    let mut hmap_b = HashMap::new();
    let mut left_side = vec![];
    for line in content.lines() {
        let vals: Vec<_> = line.split_whitespace().map(|x|x.parse::<i32>().unwrap()).collect();
        hmap_a.insert(vals[0], hmap_a.get(&vals[0]).unwrap_or(&0) + 1);
        hmap_b.insert(vals[1], hmap_b.get(&vals[1]).unwrap_or(&0) + 1);
        left_side.push(vals[0])
    }
    left_side.iter().fold(0, |acc, x| acc + match hmap_b.get(&x) {
        Some(j) => j * x,
        _ => 0
    })
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn day1_input1() {
        let result = day1_1("inputs/day1-test.txt");
        println!("{}", result);
        assert_eq!(result, 11);
    }
    #[test]
    fn day1_input2() {
        let result = day1_2("inputs/day1-test.txt");
        println!("{}", result);
        assert_eq!(result, 31);
    }
}

