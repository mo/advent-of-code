import { main, sum } from './utils.js'

const part1 = (input) => {
  const parsed = input.split('\n').map((line) => line.split(/\s+/).map(Number))
  const first = parsed.map((entry) => entry[0]).sort()
  const second = parsed.map((entry) => entry[1]).sort()
  return sum(first.map((_, i) => Math.abs(first[i] - second[i])))
}

const part2 = (input) => {
  const parsed = input.split('\n').map((line) => line.split(/\s+/).map(Number))
  const first = parsed.map((entry) => entry[0])
  const second = parsed.map((entry) => entry[1])
  return sum(first.map((f) => f * second.filter((s) => s === f).length))
}

main(1, part1, part2)
