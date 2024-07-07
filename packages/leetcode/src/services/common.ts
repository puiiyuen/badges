import { SubmissionStats } from '../adapters/leetcode'

export type DifficultyConfig = {
  position: number
  title: string
  color: string
}

export type DifficultyConfigs = Record<
  SubmissionStats['difficulty'],
  DifficultyConfig
>

export const difficultyConfigs: DifficultyConfigs = {
  All: {
    position: 0,
    title: 'All',
    color: 'blue',
  },
  Easy: {
    position: 1,
    title: 'Easy',
    color: 'brightgreen',
  },
  Medium: {
    position: 2,
    title: 'Medium',
    color: 'orange',
  },
  Hard: {
    position: 3,
    title: 'Hard',
    color: 'red',
  },
}


