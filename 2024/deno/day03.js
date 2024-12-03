import { main, sum } from './utils.js'

const part1 = (input) => sum(Array.from(input.matchAll(/mul\(([0-9]{1,3}),([0-9]{1,3})\)/g), (m) => m[1] * m[2]))

const part2 = (input) => {
  let skip = false
  return sum(Array.from(input.matchAll(/do\(\)|don't\(\)|mul\(([0-9]{1,3}),([0-9]{1,3})\)/g), (m) => {
    if (m[0] === 'do()') {
      skip = false
    } else if (m[0] === `don't()`) {
      skip = true
    } else if (!skip) {
      return m[1] * m[2]
    }
    return 0
  }))
}

main(3, part1, part2)
