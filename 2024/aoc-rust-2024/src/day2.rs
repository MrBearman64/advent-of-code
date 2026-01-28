use std::{collections::HashMap, fs};

pub fn day2_1(path: &str) -> i32 {
    let content = fs::read_to_string(path).expect("Could not find input file!");
    let mut safe = 0;
    for line in content.lines() {
        let vals: Vec<_> = line
            .split_whitespace()
            .map(|x| x.parse::<i32>().unwrap())
            .collect();
        let mut prev = vals[0];
        let mut direction = 0;
        let mut safety_run = 1;
        for val in vals[1..].iter() {
            let diff = val - prev;
            if direction == 0 {
                direction = diff.signum();
            }
            if diff.signum() != direction {
                safety_run = 0;
                break;
            }

            if diff.abs() < 1 || diff.abs() > 3 {
                safety_run = 0;
                break;
            }
            prev = *val;
        }
        safe += safety_run;
    }
    safe
}

pub fn day2_2(path: &str) -> i32 {
    let content = fs::read_to_string(path).expect("Could not find input file!");
    content
        .lines()
        .map(|line| -> i32 {
            let vals: Vec<_> = line
                .split_whitespace()
                .map(|x| x.parse::<i32>().unwrap())
                .collect();
            let analysis = vals[..(vals.len() - 1)].iter().zip(vals[1..].iter()).fold(
                HashMap::new(),
                |mut acc, (x, y)| {
                    let diff = match y - x {
                        d if d.abs() > 3 => 2,
                        d => d.signum(),
                    };
                    let counter = acc.entry(diff).or_insert(0);
                    *counter += 1;
                    acc
                },
            );
            println!("{:?}", analysis);
            match (
                analysis.get(&1),
                analysis.get(&0),
                analysis.get(&-1),
                analysis.get(&2),
            ) {
                (_, _, _, Some(_)) => 0,
                (Some(&asc), Some(&x), Some(&desc), _) if asc.min(desc) + x > 1 => 0,
                (Some(&asc), _, Some(&desc), _) if asc.min(desc) > 1 => 0,
                (_, Some(&x), _, _) if x > 1 => 0,
                _ => 1,
            }
        })
        .sum()
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn part1() {
        assert_eq!(day2_1("inputs/day2-test.txt"), 2);
    }

    #[test]
    fn part2() {
        assert_eq!(day2_2("inputs/day2-test.txt"), 4);
    }
}
