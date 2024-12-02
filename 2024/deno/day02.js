import { main, sliding } from './utils.js'

const safe = (nums) =>
  Math.min(
    ...sliding(nums, 2).reduce(([incs, decs], [a, b]) => {
      switch (true) {
        case a < b && b - a <= 3:
          return [incs + 1, decs]
        case a > b && a - b <= 3:
          return [incs, decs + 1]
        default:
          return [incs + 1, decs + 1]
      }
    }, [0, 0]),
  ) === 0

const part1 = (input) => input.split('\n').filter((report) => safe(report.split(' ').map(Number))).length

const part2 = (input) =>
  input.split('\n').filter((report) => {
    const nums = report.split(' ').map(Number)
    return safe(nums) || Array(nums.length).fill().some((_, i) => safe(nums.toSpliced(i, 1)))
  }).length

main(2, part1, part2)
