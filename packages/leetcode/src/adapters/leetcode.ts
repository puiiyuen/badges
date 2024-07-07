import axios from 'axios'

export const difficulties = ['All', 'Easy', 'Medium', 'Hard'] as const

export type SubmissionStats = QuestionCount & {
  submissions: number
}

export interface QuestionCount {
  difficulty: (typeof difficulties)[number]
  count: number
}

export interface LeetCodeUser {
  username: string
  ranking: number
  reputation: number
  allQuestionsCount: QuestionCount[]
  acSubmissionNum: SubmissionStats[]
  totalSubmissionNum: SubmissionStats[]
}

export class LeetCodeStats {
  #graphqlEndpoint = 'https://leetcode.com/graphql'

  public async fetch(username: string): Promise<LeetCodeUser> {
    const result = await this.fetchFromLeetCode(username)
    const jsonNode = result.data
    const matchedUser = jsonNode.matchedUser

    const leetCodeUser: LeetCodeUser = {
      username: matchedUser.username,
      ranking: matchedUser.profile.ranking,
      reputation: matchedUser.profile.reputation,
      allQuestionsCount: jsonNode.allQuestionsCount,
      acSubmissionNum: matchedUser.submitStats.acSubmissionNum,
      totalSubmissionNum: matchedUser.submitStats.totalSubmissionNum,
    }

    return leetCodeUser
  }

  private async fetchFromLeetCode(username: string): Promise<any> {
    const query = `
      query getUserProfile($username: String!) {
        allQuestionsCount {
          difficulty
          count
        }
        matchedUser(username: $username) {
          username
          profile {
            reputation
            ranking
          }
          submitStats: submitStatsGlobal {
            acSubmissionNum {
              difficulty
              count
              submissions
            }
            totalSubmissionNum {
              difficulty
              count
              submissions
            }
          }
        }
      }
    `

    const response = await axios.post(
      this.#graphqlEndpoint,
      {
        query,
        variables: { username },
      },
      {
        headers: {
          'Content-Type': 'application/json',
        },
      }
    )

    if (response.status !== 200) {
      throw new Error(`HTTP error! status: ${response.status}`)
    }

    return response.data
  }
}
