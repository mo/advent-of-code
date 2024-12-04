import { main, matches, sum } from './utils.js'

const count = (x) => Array.isArray(x) ? sum(x.map(count)) : matches(x, /XMAS/g).length + matches(x, /SAMX/g).length
const rows = (input) => input.split('\n')
const cols = (input) => {
  const rws = rows(input)
  return Array(rws[0].length).fill().map((_, colIdx) => rws.map((row) => row[colIdx]).join(''))
}
const diagonalsNWSE = (input) => {
  const rws = rows(input)
  const cells = rws.map((row) => row.split(''))
  const diagonalCount = cells.length + cells[0].length - 1
  return Array(diagonalCount).fill().flatMap((_, dIdx) =>
    Array(rws.length).fill().flatMap((_, dOffset) => {
      const x = dIdx - dOffset
      const y = dOffset
      return x >= 0 && x < rws[0].length ? [cells[y][x]] : []
    }).join('')
  )
}

const diagonals = (input) =>
  diagonalsNWSE(input).concat(
    diagonalsNWSE(input.split('\n').map((str) => str.split('').toReversed().join('')).join('\n')),
  )

const part1 = (input) => count(rows(input)) + count(cols(input)) + count(diagonals(input))

const part2 = (input) => {
  const rws = rows(input)
  const cells = rws.map((row) => row.split(''))
  let found = 0
  for (let y = 1; y < rws.length - 1; ++y) {
    for (let x = 1; x < cells[0].length - 1; ++x) {
      if (
        cells[y][x] === 'A' &&
        ((cells[y - 1][x - 1] === 'M' && cells[y + 1][x + 1] === 'S') ||
          (cells[y - 1][x - 1] === 'S' && cells[y + 1][x + 1] === 'M')) &&
        ((cells[y - 1][x + 1] === 'M' && cells[y + 1][x - 1] === 'S') ||
          (cells[y - 1][x + 1] === 'S' && cells[y + 1][x - 1] === 'M'))
      ) {
        found += 1
      }
    }
  }
  return found
}

main(4, part1, part2)
