import { LeetCodeStats, LeetCodeUser } from '../adapters/leetcode'
import axios from 'axios'

export class ShieldBuilder {
  #leetCodeStats: LeetCodeStats = new LeetCodeStats()
  shieldsUrl = 'https://img.shields.io/badge/'

  async build(
    username: string,
    badgeContent: (leetCodeUser: LeetCodeUser) => string
  ): Promise<string> {
    const leetCodeUser = await this.#leetCodeStats.fetch(username)
    const badge = await axios.get(this.shieldsUrl + badgeContent(leetCodeUser))
    return badge.data
  }
}
